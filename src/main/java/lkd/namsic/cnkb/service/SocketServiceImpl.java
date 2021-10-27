package lkd.namsic.cnkb.service;

import lkd.namsic.cnkb.exception.CommonException;
import lkd.namsic.cnkb.service.socket.SocketConnectService;
import lkd.namsic.cnkb.service.socket.SocketDataService;
import lkd.namsic.cnkb.service.socket.SocketDisconnectService;
import lkd.namsic.cnkb.socket.SocketData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
@Primary
public class SocketServiceImpl implements SocketService {

    @Autowired
    SocketTokenService socketTokenService;

    public static final Map<String, SocketService> serviceMap = new LinkedHashMap<>();

    //TODO: Change non-token api to token api

    public SocketServiceImpl() {
        serviceMap.put("connect", new SocketConnectService());
        serviceMap.put("disconnect", new SocketDisconnectService());
        serviceMap.put("data", new SocketDataService());
    }

    @Override
    public SocketData.Output handleData(@NonNull SocketData.Input input, @NonNull WebSocketSession session) {
        String request = input.getRequest();
        Long playerId = input.getPlayerId();

        try {
            if(request == null) {
                throw new CommonException(412, "Requires request");
            } else if(playerId == null) {
                throw new CommonException(412, "Requires playerId");
            }

            SocketService service = serviceMap.get(request);
            if(service == null) {
                throw new CommonException(400, "Unknown request - " + request);
            }

            SocketData.Output output;
            if(service instanceof SocketTokenService tokenService) {
                output = socketTokenService.handleData(input, session, tokenService);
            } else {
                output = service.handleData(input, session);
            }

            log.info("Socket - {} {} {}", request, playerId, output.getStatus());

            return output;
        } catch (CommonException e) {
            log.info("Socket Common Error - {} {} {}", request, playerId, e.getStatus());

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
