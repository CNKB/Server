package lkd.namsic.cnkb.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class Response {
    
    @Builder.Default
    Integer status = HttpStatus.OK.value();
    
    Object data;
    
    String message;
    
}