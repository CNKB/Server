package lkd.namsic.cnkb.service;

import lkd.namsic.cnkb.exception.CommonException;
import lkd.namsic.cnkb.service.socket.SocketConnectService;
import lkd.namsic.cnkb.service.socket.SocketDisconnectService;
import lkd.namsic.cnkb.socket.SocketData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
public class SocketServiceImpl implements SocketService {

    private final Map<String, SocketService> serviceMap = new LinkedHashMap<>();

    public SocketServiceImpl() {
        serviceMap.put("connect", new SocketConnectService());
        serviceMap.put("disconnect", new SocketDisconnectService());
    }

    @Override
    public SocketData.Output handleData(@NonNull SocketData.Input input, @NonNull WebSocketSession session) {
        try {
            String request = input.getRequest();
            Long playerId = input.getPlayerId();

            if(request == null) {
                throw new CommonException(412, "Requires request");
            } else if(playerId == null) {
                throw new CommonException(412, "Requires playerId");
            }

            SocketService service = serviceMap.get(request);
            if(service == null) {
                throw new CommonException(400, "Unknown request - " + request);
            }

            return service.handleData(input, session);
        } catch (CommonException e) {
            return SocketData.Output
                    .builder()
                    .status(e.getStatus())
                    .message(e.getMessage())
                    .build();
        } catch (Exception e) {
            return SocketData.Output
                    .builder()
                    .status(500)
                    .message(e.getMessage())
                    .build();
        }
    }

}
