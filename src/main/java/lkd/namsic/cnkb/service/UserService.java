package lkd.namsic.cnkb.service;

import lkd.namsic.cnkb.domain.User;
import lkd.namsic.cnkb.dto.UserInput;
import lkd.namsic.cnkb.dto.response.Response;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    Response signIn(HttpServletRequest request, UserInput.SignInInput input);
    Response getPlayers(HttpServletRequest request, User user);
    Response getToken(String tokenInput);

}
