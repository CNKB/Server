package lkd.namsic.cnkb;

import lkd.namsic.cnkb.dto.response.Response;
import lkd.namsic.cnkb.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

@Slf4j
public class Config {

    //TODO: swagger

    private static final Config instance = new Config();

    @NonNull
    public static Config getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public void checkRole(@NonNull HttpServletRequest request, String...requiredRoles) {
        List<String> roleSet = (List<String>) request.getAttribute("roles");

        if(roleSet == null) {
            throw new CommonException(403, "Forbidden resource");
        }

        for(String requiredRole : requiredRoles) {
            if(!roleSet.contains(requiredRole)) {
                throw new CommonException(401, "Unauthorized");
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Response> T safeCall(@NonNull String methodName, @NonNull Callable<T> callable) {
        try {
            return callable.call();
        } catch (CommonException e) {
            return (T) T.builder()
                    .status(e.getStatus())
                    .data(e.getMessage())
                    .build();
        } catch (Exception e) {
            log.error(methodName, e);

            return (T) T.builder()
                    .status(500)
                    .data("Unknown Error")
                    .build();
        }
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

    public long ipToLong(@NonNull String ipString) {
        long ip = 0L;

        String[] split = ipString.split("\\.");
        if(split.length != 4) {
            throw new RuntimeException("Weird ip string - " + ipString);
        }

        ip |= Long.parseLong(split[0]) << 27;
        ip |= Long.parseLong(split[1]) << 18;
        ip |= Long.parseLong(split[2]) << 9;
        ip |= Long.parseLong(split[3]);

        return ip;
    }

    @NonNull
    public String longToIp(long ip) {
        String ipString = "";

        short ander = 0b111111111;
        ipString += (ip >> 27 & ander) + "." +
                (ip >> 18 & ander) + "." +
                (ip >> 9 & ander) + "." +
                (ip & ander);

        return ipString;
    }

    public <T extends Response> ResponseEntity<T> getResponseEntity(T response) {
        return new ResponseEntity<T>(response, HttpStatus.valueOf(response.getStatus()));
    }

}
