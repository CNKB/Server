package lkd.namsic.cnkb.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StatusException extends CommonException {
    
    int status;
    String message;
    
    public StatusException(int status, String message) {
        this.status = status;
        this.message = message;
    }
    
}