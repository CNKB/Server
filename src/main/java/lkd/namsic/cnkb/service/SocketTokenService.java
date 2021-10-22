package lkd.namsic.cnkb.service;

import lkd.namsic.cnkb.exception.CommonException;
import lkd.namsic.cnkb.socket.SocketData;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketSession;

public interface SocketTokenService extends SocketService {

    default SocketData.Output handleData(@NonNull SocketData.Input input, @NonNull WebSocketSession session,
                                         @NonNull SocketTokenService service) throws CommonException {
        return SocketData.Output
                .builder()
                .status(405)
                .message("Cannot use this method")
                .build();
    }

}
