package lkd.namsic.cnkb.service.socket;

import lkd.namsic.cnkb.config.Config;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.socket.SocketOutput;
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
    public SocketOutput handleData(@NonNull Player player, @NonNull WebSocketSession session) {
        long playerId = player.getId();
        String sessionId = session.getId();
        Map<String, Object> data = new HashMap<>();

        if(SocketHandler.sessionMap.remove(sessionId) != null) {
            return SocketOutput
                    .builder()
                    .status(-1)
                    .message("Multi connection with playerId - " + playerId)
                    .data(data)
                    .build();
        }

        SocketHandler.sessionMap.put(sessionId, playerId);
        data.put("version", config.VERSION);

        return SocketOutput
                .builder()
                .message("Success")
                .data(data)
                .build();
    }

}
