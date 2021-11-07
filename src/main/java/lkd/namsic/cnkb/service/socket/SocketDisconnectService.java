package lkd.namsic.cnkb.service.socket;

import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.socket.SocketOutput;
import lkd.namsic.cnkb.controller.SocketHandler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class SocketDisconnectService implements SocketService {

    @Override
    public SocketOutput handleData(@NonNull Player player, @NonNull WebSocketSession session) {
        SocketHandler.sessionMap.remove(session.getId());

        return SocketOutput
                .builder()
                .message("Success")
                .build();
    }

}
