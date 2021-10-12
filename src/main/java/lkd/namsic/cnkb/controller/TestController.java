package lkd.namsic.cnkb.controller;

import lkd.namsic.cnkb.Config;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/client-ip")
    public String getClientIp(HttpServletRequest request) {
        return Config.getInstance().getIp(request);
    }

}
