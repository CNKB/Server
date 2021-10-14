package lkd.namsic.cnkb.controller;

import lkd.namsic.cnkb.Config;
import lkd.namsic.cnkb.dto.response.Response;
import lkd.namsic.cnkb.dto.response.TestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/client-ip")
    public ResponseEntity<TestResponse> getClientIp(HttpServletRequest request) {
        return new ResponseEntity<>(TestResponse.builder()
                .data(Config.getInstance().getIp(request))
                .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/t")
    public ResponseEntity<Response> tokenRoleTest(HttpServletRequest request) {
        Response response = Config.getInstance().safeCall("tokenRoleTest", () -> {
            Config.getInstance().checkRole(request, "user");
            return Response.builder().build();
        });

        return Config.getInstance().getResponseEntity(response);
    }

}
