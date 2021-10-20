package lkd.namsic.cnkb.service.socket;

import lkd.namsic.cnkb.service.SocketService;
import lkd.namsic.cnkb.socket.SocketData;
import lkd.namsic.cnkb.socket.SocketHandler;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketSession;

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
