package lkd.namsic.cnkb.dto.socket;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
public class SocketResponse {
    
    @NonNull
    @Builder.Default
    Integer status = HttpStatus.OK.value();
    
    @Setter
    String request;
    
    @Builder.Default
    String message = "Success";
    
    @Builder.Default
    Map<String, Object> data = new HashMap<>();
    
}