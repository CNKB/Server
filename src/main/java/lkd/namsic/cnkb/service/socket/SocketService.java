package lkd.namsic.cnkb.service.socket;

import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.socket.SocketOutput;
import lkd.namsic.cnkb.exception.SessionCloseException;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

public interface SocketService {
    
    @NonNull
    String getRequest();
    
    default boolean invalidOnDoing() {
        return false;
    }
    
    @NonNull
    SocketOutput handleData(
        @NonNull Player player,
        @NonNull WebSocketSession session,
        @NonNull Map<String, Object> inputData
    ) throws SessionCloseException;
    
}