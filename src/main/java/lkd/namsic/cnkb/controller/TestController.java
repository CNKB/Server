package lkd.namsic.cnkb.controller;

import lkd.namsic.cnkb.config.Config;
import lkd.namsic.cnkb.dto.response.Response;
import lkd.namsic.cnkb.dto.response.TestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private Config config;

    @GetMapping("/client-ip")
    public ResponseEntity<TestResponse> getClientIp(HttpServletRequest request) {
        return new ResponseEntity<>(TestResponse.builder()
                .data(config.getIp(request))
                .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/t")
    public ResponseEntity<Response> tokenRoleTest(HttpServletRequest request) {
        Response response = config.safeCall("tokenRoleTest", () -> {
            config.checkRole(request, "user");
            return Response.builder().data("Success").build();
        });

        return config.getResponseEntity(response);
    }

}
