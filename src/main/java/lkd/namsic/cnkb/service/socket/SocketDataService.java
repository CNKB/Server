package lkd.namsic.cnkb.service.socket;

import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.socket.SocketInnerData;
import lkd.namsic.cnkb.dto.socket.SocketOutput;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Component
public class SocketDataService implements SocketService {

    @Override
    public SocketOutput handleData(@NonNull Player player, @NonNull WebSocketSession session) {
        Map<String, Object> data = new HashMap<>();

        data.put("category",
                new HashMap<String, Object>() {{
                    put("upDown", new SocketInnerData.UpDownButton("game.up_down", 1, 1));//TODO
                }}
        );

        return SocketOutput
                .builder()
                .message("Success")
                .data(data)
                .build();
    }

}
