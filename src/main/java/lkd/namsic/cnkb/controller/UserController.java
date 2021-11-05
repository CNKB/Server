package lkd.namsic.cnkb.controller;

import lkd.namsic.cnkb.config.Config;
import lkd.namsic.cnkb.domain.User;
import lkd.namsic.cnkb.dto.UserInput;
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
    private Config config;

    @Autowired
    private UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<Response> signIn(HttpServletRequest request,
                                           @RequestBody UserInput.SignInInput input) {
        Response response = userService.signIn(request, input);
        log.info("signIn - {} {}", response.getStatus(), input.toString());
        return config.getResponseEntity(response);
    }

    @GetMapping("/players/t")
    public ResponseEntity<Response> getPlayers(HttpServletRequest request,
                                               @RequestBody(required = false) User user) {
        Response response = userService.getPlayers(request, user);
        log.info("getPlayers - {} {}", response.getStatus(), request.getAttribute("id"));
        return config.getResponseEntity(response);
    }

    @GetMapping("/token")
    public ResponseEntity<Response> getToken(@RequestHeader(value = "refreshToken") String refreshToken) {
        Response response = userService.getToken(refreshToken);
        log.info("getToken - {} {}", response.getStatus(), refreshToken);
        return config.getResponseEntity(response);
    }

}
