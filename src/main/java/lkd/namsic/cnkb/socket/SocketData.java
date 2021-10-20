package lkd.namsic.cnkb.socket;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class SocketData {

    @Getter
    @ToString
    public static class Input {
        Long playerId;
        String request;
        Map<String, Object> data = new HashMap<>();
    }

    @Getter
    @Builder
    public static class Output {

        @Builder.Default
        Integer status = HttpStatus.OK.value();

        String message;

        Map<String, Object> data;

    }

}
