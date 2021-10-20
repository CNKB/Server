package lkd.namsic.cnkb.service;

import lkd.namsic.cnkb.socket.SocketData;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketSession;

public interface SocketService {

    SocketData.Output handleData(@NonNull SocketData.Input input, @NonNull WebSocketSession session);

}
