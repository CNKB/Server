package lkd.namsic.cnkb.service.socket;

import lkd.namsic.cnkb.service.SocketService;
import lkd.namsic.cnkb.dto.SocketData;
import lkd.namsic.cnkb.controller.SocketHandler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class SocketDisconnectService implements SocketService {

    @Override
    public SocketData.Output handleData(@NonNull SocketData.Input input, @NonNull WebSocketSession session) {
        long playerId = input.getPlayerId();

        SocketHandler.sessionIdMap.remove(session.getId());
        SocketHandler.sessionMap.remove(playerId);

        return SocketData.Output
                .builder()
                .message("Success")
                .build();
    }

}
