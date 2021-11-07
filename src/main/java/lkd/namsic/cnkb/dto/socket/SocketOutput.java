package lkd.namsic.cnkb.dto.socket;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Builder
public class SocketOutput {
    
    @NonNull
    @Builder.Default
    Integer status = HttpStatus.OK.value();
    
    @NonNull
    String message;
    
    @NonNull
    Map<String, Object> data;
    
}