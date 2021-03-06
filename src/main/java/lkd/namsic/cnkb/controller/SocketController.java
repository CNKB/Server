package lkd.namsic.cnkb.controller;

import lkd.namsic.cnkb.bearer.JwtTokenProvider;
import lkd.namsic.cnkb.config.Config;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.socket.SocketRequest;
import lkd.namsic.cnkb.dto.socket.SocketResponse;
import lkd.namsic.cnkb.enums.Doing;
import lkd.namsic.cnkb.enums.LogType;
import lkd.namsic.cnkb.exception.StatusException;
import lkd.namsic.cnkb.exception.SessionCloseException;
import lkd.namsic.cnkb.handler.SocketHandler;
import lkd.namsic.cnkb.repository.PlayerRepository;
import lkd.namsic.cnkb.service.socket.SocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class SocketController {
    
    private static Map<String, SocketService> serviceMap;
    private final List<SocketService> socketServiceList;
    
    private final Config config;
    private final JwtTokenProvider jwtTokenProvider;
    private final PlayerRepository playerRepository;
    
    @PostConstruct
    public void init() {
        serviceMap = socketServiceList.stream()
            .collect(Collectors.toMap(
                SocketService::getRequest,
                service -> service
            ));
    }
    
    private void checkToken(@NonNull String accessToken) throws StatusException {
        long userId = jwtTokenProvider.validateToken(accessToken);
        
        if(userId == 0) {
            throw new StatusException(401, "Unauthorized");
        }
    }
    
    @Transactional
    public synchronized void executeService (
        @NonNull SocketRequest request,
        @NonNull WebSocketSession session
    ) throws SessionCloseException {
        String requestName = request.getRequest();
        Long playerId = request.getPlayerId();
        String accessToken = request.getAccessToken();
        
        try {
            if(requestName == null) {
                throw new StatusException(412, "Requires request");
            } else if(playerId == null) {
                throw new StatusException(412, "Requires playerId");
            } else if(accessToken == null) {
                throw new StatusException(412, "Requires accessToken");
            }
            
            checkToken(accessToken);
            
            SocketService service = serviceMap.get(requestName);
            if(service == null) {
                throw new StatusException(400, "Unknown request - " + requestName);
            }
            
            Player player = playerRepository.findById(playerId).orElseThrow(
                () -> new StatusException(401, "Unknown player")
            );
    
            if(!(player.getDoing() != Doing.NONE.value && service.invalidOnDoing())) {
                Map<String, Object> inputData = request.getData();
                String requiredArg;
                Class<?> c;
                
                for(Map.Entry<String, Class<?>> entry : service.getRequiredArgs().entrySet()) {
                    requiredArg = entry.getKey();
                    c = entry.getValue();
                    Object data = inputData.get(requiredArg);
                    
                    if(data == null) {
                        config.log(
                            LogType.MISSING_ARG, player,
                            "Catch: " + requiredArg + ", Got: " + inputData
                        );
                        throw new StatusException(412, "Requires " + requiredArg);
                    } else if(!data.getClass().equals(c)) {
                        config.log(
                            LogType.WRONG_ARG_TYPE, player,
                            "Arg " + requiredArg + " Catch: " + data.getClass().getName() + ", Requires: " + c.getName()
                        );
                    }
                }
                
                new Thread(() -> {
                    try {
                        try {
                            SocketResponse response = service.handleData(player, session, inputData);
                            response.setRequest(requestName);
        
                            SocketHandler.sendMessage(session, response);
                        } catch(SessionCloseException e) {
                            session.close();
                        } catch(Exception e) {
                            session.close();
                            config.error("Error occurred while handling data", e);
                        }
                    } catch(Exception e) {
                        config.error("Error occurred while closing session", e);
                    }
                }).start();
            }
        } catch(StatusException e) {
            log.info("Common Exception - {} {}", e.getMessage(), playerId);
            
            SocketHandler.sendMessage(
                session,
                SocketResponse
                    .builder()
                    .status(e.getStatus())
                    .message(e.getMessage())
                    .build()
            );
        } catch(Exception e) {
            config.error("Error occurred", e);
            
            SocketHandler.sendMessage(
                session,
                SocketResponse
                    .builder()
                    .status(500)
                    .message(e.getMessage())
                    .build()
            );
        }
    }
    
}