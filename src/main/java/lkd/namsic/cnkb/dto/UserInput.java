package lkd.namsic.cnkb.dto;

import lombok.Getter;
import lombok.ToString;

public class UserInput {

    @Getter
    @ToString
    public static class SignInInput {
        String email;
        String provider;
        String uid;
    }

}
