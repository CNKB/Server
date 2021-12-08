package lkd.namsic.cnkb.service.socket;

import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.socket.SocketResponse;
import lkd.namsic.cnkb.exception.SessionCloseException;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

public interface SocketService {
    
    @NonNull
    String getRequest();
    
    @NonNull
    Map<String, Class<?>> getRequiredArgs();
    
    default boolean invalidOnDoing() {
        return false;
    }
    
    @NonNull
    SocketResponse handleData(
        @NonNull Player player,
        @NonNull WebSocketSession session,
        @NonNull Map<String, Object> inputData
    ) throws SessionCloseException;
    
}