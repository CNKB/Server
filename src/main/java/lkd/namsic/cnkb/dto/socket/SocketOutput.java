package lkd.namsic.cnkb.dto.socket;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;

@Getter
@Builder
public class SocketOutput {
    
    @NonNull
    @Builder.Default
    Integer status = HttpStatus.OK.value();
    
    @Setter
    String request;
    
    String message;
    
    @SuppressWarnings("unchecked")
    @Builder.Default
    Map<String, Object> data = Collections.EMPTY_MAP;
    
}