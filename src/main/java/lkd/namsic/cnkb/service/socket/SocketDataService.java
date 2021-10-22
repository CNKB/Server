package lkd.namsic.cnkb.service.socket;

import lkd.namsic.cnkb.service.SocketTokenService;
import lkd.namsic.cnkb.socket.SocketData;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

public class SocketDataService implements SocketTokenService {

    @Override
    public SocketData.Output handleData(@NonNull SocketData.Input input, @NonNull WebSocketSession session) {
        Map<String, Object> data = new HashMap<>();

        return SocketData.Output
                .builder()
                .message("Success")
                .data(data)
                .build();
    }

}
