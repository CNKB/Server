package lkd.namsic.cnkb.dto.user;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignInInput {

    String email;
    String provider;
    String uid;

}
