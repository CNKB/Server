package lkd.namsic.cnkb.service.socket;

import lkd.namsic.cnkb.service.SocketService;
import lkd.namsic.cnkb.dto.SocketData;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Component
public class SocketDataService implements SocketService {

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
