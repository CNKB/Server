package lkd.namsic.cnkb.controller;

import lkd.namsic.cnkb.bearer.JwtTokenProvider;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.socket.SocketInput;
import lkd.namsic.cnkb.dto.socket.SocketOutput;
import lkd.namsic.cnkb.exception.CommonException;
import lkd.namsic.cnkb.repository.PlayerRepository;
import lkd.namsic.cnkb.service.socket.SocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class SocketDispatcher {
    
    private static Map<String, SocketService> serviceMap;
    private final JwtTokenProvider jwtTokenProvider;
    private final PlayerRepository playerRepository;
    private final List<SocketService> socketServiceList;
    
    @PostConstruct
    public void init() {
        serviceMap = socketServiceList.stream()
            .collect(Collectors.toMap(
                SocketService::getRequest,
                service -> service
            ));
    }
    
    private void checkToken(@NonNull String accessToken) throws CommonException {
        long userId = jwtTokenProvider.validateToken(accessToken);
        
        if(userId == 0) {
            throw new CommonException(401, "Unauthorized");
        }
    }
    
    public SocketOutput executeService(@NonNull SocketInput input,
                                       @NonNull WebSocketSession session) {
        String request = input.getRequest();
        Long playerId = input.getPlayerId();
        String accessToken = input.getAccessToken();
        
        try {
            if(request == null) {
                throw new CommonException(412, "Requires request");
            } else if(playerId == null) {
                throw new CommonException(412, "Requires playerId");
            } else if(accessToken == null) {
                throw new CommonException(412, "Requires accessToken");
            }
            
            checkToken(accessToken);
            
            SocketService service = serviceMap.get(request);
            if(service == null) {
                throw new CommonException(400, "Unknown request - " + request);
            }
            
            Player player = playerRepository.findById(playerId).orElseThrow(RuntimeException::new);
            SocketOutput output = service.handleData(player, session);
            
            output.setRequest(request);
            return output;
        } catch(CommonException e) {
            log.info("Common Exception - {} {}", e.getMessage(), playerId);
            
            return SocketOutput
                .builder()
                .status(e.getStatus())
                .message(e.getMessage())
                .build();
        } catch(Exception e) {
            e.printStackTrace();
            
            return SocketOutput
                .builder()
                .status(500)
                .message(e.getMessage())
                .build();
        }
    }
    
}