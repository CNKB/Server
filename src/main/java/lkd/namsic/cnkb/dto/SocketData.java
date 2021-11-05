package lkd.namsic.cnkb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class SocketData {

    @Getter
    @Setter
    @ToString
    public static class Input {
        Long playerId;
        String request;
        String accessToken;
        final Map<String, Object> data = new HashMap<>();
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
