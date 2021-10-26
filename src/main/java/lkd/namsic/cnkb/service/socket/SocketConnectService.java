package lkd.namsic.cnkb.service.socket;

import lkd.namsic.cnkb.Config;
import lkd.namsic.cnkb.service.SocketService;
import lkd.namsic.cnkb.socket.SocketData;
import lkd.namsic.cnkb.socket.SocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

public class SocketConnectService implements SocketService {

    @Autowired
    Config config;

    @Override
    public SocketData.Output handleData(@NonNull SocketData.Input input, @NonNull WebSocketSession session) {
        long playerId = input.getPlayerId();

        SocketHandler.sessionIdMap.put(session.getId(), playerId);
        SocketHandler.sessionMap.put(playerId, session);

        Map<String, Object> data = new HashMap<>();
        data.put("version", config.VERSION);

        return SocketData.Output
                .builder()
                .message("Success")
                .data(data)
                .build();
    }

}
