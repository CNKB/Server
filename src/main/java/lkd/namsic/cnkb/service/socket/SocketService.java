package lkd.namsic.cnkb.service.socket;

import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.socket.SocketOutput;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketSession;

public interface SocketService {

    @NonNull
    String getRequest();

    SocketOutput handleData(@NonNull Player player, @NonNull WebSocketSession session);

}
