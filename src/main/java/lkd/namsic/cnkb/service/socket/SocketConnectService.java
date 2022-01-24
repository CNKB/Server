package lkd.namsic.cnkb.service.socket;

import lkd.namsic.cnkb.config.Config;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.socket.SocketResponse;
import lkd.namsic.cnkb.enums.LogType;
import lkd.namsic.cnkb.exception.SessionCloseException;
import lkd.namsic.cnkb.handler.SocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SocketConnectService implements SocketService {

    private final Config config;

    @NonNull
    @Override
    public String getRequest() {
        return "connect";
    }
    
    @NonNull
    @Override
    public Map<String, Class<?>> getRequiredArgs() { return new HashMap<>(); }
    
    @Override
    public SocketResponse handleData(@NonNull Player player, @NonNull WebSocketSession session,
                                     @NonNull Map<String, Object> inputData) {
        long playerId = player.getId();
        String sessionId = session.getId();
        Map<String, Object> data = new HashMap<>();

        if(SocketHandler.sessionMap.remove(sessionId) != null) {
            SocketHandler.sendMessage(
                session,
                SocketResponse
                    .builder()
                    .status(-1)
                    .message("Multi connection with playerId - " + playerId)
                    .data(data)
                    .build()
            );
            throw new SessionCloseException();
        }

        SocketHandler.sessionMap.put(sessionId, playerId);
        data.put("version", config.VERSION);
        
        config.log(LogType.CONNECT, player, String.valueOf(playerId));

        return SocketResponse
            .builder()
            .data(data)
            .build();
    }

}