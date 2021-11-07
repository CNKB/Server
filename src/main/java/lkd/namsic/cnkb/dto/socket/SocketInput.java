package lkd.namsic.cnkb.dto.socket;

import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class SocketInput {

    Long playerId;
    String request;
    String accessToken;
    final Map<String, Object> data = new HashMap<>();

}