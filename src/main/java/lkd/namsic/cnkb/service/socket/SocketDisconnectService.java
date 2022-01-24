package lkd.namsic.cnkb.service.socket;

import lkd.namsic.cnkb.config.Config;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.socket.SocketResponse;
import lkd.namsic.cnkb.enums.LogType;
import lkd.namsic.cnkb.handler.SocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SocketDisconnectService implements SocketService {
    
    private final Config config;

    @NonNull
    @Override
    public String getRequest() {
        return "disconnect";
    }
    
    @NonNull
    @Override
    public Map<String, Class<?>> getRequiredArgs() { return new HashMap<>(); }

    @Override
    public SocketResponse handleData(@NonNull Player player, @NonNull WebSocketSession session,
                                     @NonNull Map<String, Object> inputData) {
        SocketHandler.sessionMap.remove(session.getId());
        config.log(LogType.DISCONNECT, player, String.valueOf(player.getId()));
        
        return SocketResponse.builder().build();
    }

}