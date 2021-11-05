package lkd.namsic.cnkb.service.socket;

import lkd.namsic.cnkb.config.Config;
import lkd.namsic.cnkb.service.SocketService;
import lkd.namsic.cnkb.dto.SocketData;
import lkd.namsic.cnkb.controller.SocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Component
public class SocketConnectService implements SocketService {

    @Autowired
    private Config config;

    @Override
    public SocketData.Output handleData(@NonNull SocketData.Input input, @NonNull WebSocketSession session) {
        long playerId = input.getPlayerId();

        String message;
        WebSocketSession currentSession = SocketHandler.sessionMap.remove(playerId);

        if(currentSession != null) {
            SocketHandler.sessionIdMap.remove(currentSession.getId());
            message = "Session already exists";
        } else {
            message = "Success";
        }

        SocketHandler.sessionIdMap.put(session.getId(), playerId);
        SocketHandler.sessionMap.put(playerId, session);

        Map<String, Object> data = new HashMap<>();
        data.put("version", config.VERSION);

        return SocketData.Output
                .builder()
                .message(message)
                .data(data)
                .build();
    }

}
