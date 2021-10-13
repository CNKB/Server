package lkd.namsic.cnkb.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommonException extends RuntimeException {

    int status;
    String message;

    public CommonException(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
