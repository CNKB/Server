package lkd.namsic.cnkb.controller;

import lkd.namsic.cnkb.Config;
import lkd.namsic.cnkb.domain.User;
import lkd.namsic.cnkb.dto.SignInInput;
import lkd.namsic.cnkb.dto.response.Response;
import lkd.namsic.cnkb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<Response> signIn(HttpServletRequest request,
                                           @RequestBody SignInInput input) {
        Response response = userService.signIn(request, input);
        log.info("signIn - {}", input.toString());
        return Config.getInstance().getResponseEntity(response);
    }

    @GetMapping("/players/t")
    public ResponseEntity<Response> getPlayers(HttpServletRequest request) {
        Response response = userService.getPlayers(request);
        log.info("getPlayers - {}", request.getAttribute("id"));
        return Config.getInstance().getResponseEntity(response);
    }

}
