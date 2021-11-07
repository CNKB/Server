package lkd.namsic.cnkb.dto.socket;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.util.Map;

@Getter
@Builder
public class SocketOutput {

    @Builder.Default
    Integer status = HttpStatus.OK.value();

    String message;

    @Nullable
    Map<String, Object> data;

}
