package lkd.namsic.cnkb.service.socket;

import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.socket.SocketData;
import lkd.namsic.cnkb.dto.socket.SocketOutput;
import lkd.namsic.cnkb.enums.VariableType;
import lkd.namsic.cnkb.handler.PlayerVariableHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SocketGetUIService implements SocketService {

    private final PlayerVariableHandler playerVariableHandler;

    @NonNull
    @Override
    public String getRequest() {
        return "getUI";
    }

    @Override
    public SocketOutput handleData(@NonNull Player player, @NonNull WebSocketSession session) {
        Map<String, Object> data = new HashMap<>();

        data.put(
            "mine", new HashMap<>() {{
                int maxMineLv = playerVariableHandler.getVariable(player, VariableType.MINE_LV, 1);

                put(SocketData.UpDownButton.getName(), new SocketData.UpDownButton(
                    "game.upDownButton.mineLv", 1, maxMineLv
                ));
                put(SocketData.Button.getName(), new SocketData.Button(
                    "game.button.mine"
                ));
            }}
        );

        return SocketOutput
            .builder()
            .message("Success")
            .data(data)
            .build();
    }

}