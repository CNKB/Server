package lkd.namsic.cnkb.service;

import lkd.namsic.cnkb.domain.User;
import lkd.namsic.cnkb.dto.Response;
import lkd.namsic.cnkb.dto.user.SignInInput;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    Response signIn(HttpServletRequest request, SignInInput input);
    Response getPlayers(HttpServletRequest request, User user);
    Response getToken(String tokenInput);

}
