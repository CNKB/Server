package lkd.namsic.cnkb;

import org.springframework.lang.NonNull;

import javax.servlet.http.HttpServletRequest;

public class Config {

    private static final Config instance = new Config();

    @NonNull
    public static Config getInstance() {
        return instance;
    }

    public String getIp(@NonNull HttpServletRequest request) {
        String ip;

        ip = request.getHeader("X-Forwarded-For");
        if (ip == null) ip = request.getHeader("Proxy-Client-IP");
        if (ip == null) ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip == null) ip = request.getHeader("HTTP_CLIENT_IP");
        if (ip == null) ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (ip == null) ip = request.getRemoteAddr();

        return ip;
    }

}
