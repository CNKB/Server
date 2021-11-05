package lkd.namsic.cnkb.service;

import lkd.namsic.cnkb.bearer.JwtTokenProvider;
import lkd.namsic.cnkb.exception.CommonException;
import lkd.namsic.cnkb.service.socket.SocketConnectService;
import lkd.namsic.cnkb.service.socket.SocketDataService;
import lkd.namsic.cnkb.service.socket.SocketDisconnectService;
import lkd.namsic.cnkb.dto.SocketData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
@Primary
public class SocketServiceImpl implements SocketService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private void checkToken(@NonNull String accessToken) throws CommonException {
        long userId = jwtTokenProvider.validateToken(accessToken);

        if(userId == 0) {
            throw new CommonException(401, "Unauthorized");
        }
    }

    public static Map<String, SocketService> serviceMap;

    @Autowired private SocketConnectService socketConnectService;
    @Autowired private SocketDisconnectService socketDisconnectService;
    @Autowired private SocketDataService socketDataService;

    @PostConstruct
    public void init() {
        serviceMap = new LinkedHashMap<>() {{
            put("connect", socketConnectService);
            put("disconnect", socketDisconnectService);
            put("data", socketDataService);
        }};
    }

    @Override
    public SocketData.Output handleData(@NonNull SocketData.Input input, @NonNull WebSocketSession session) {
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

            return service.handleData(input, session);
        } catch (CommonException e) {
            log.info("Common Exception - {} {}", e.getMessage(), playerId);

            return SocketData.Output
                    .builder()
                    .status(e.getStatus())
                    .message(e.getMessage())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();

            return SocketData.Output
                    .builder()
                    .status(500)
                    .message(e.getMessage())
                    .build();
        }
    }

}
