package lkd.namsic.cnkb.config;

import lkd.namsic.cnkb.domain.game.GameLog;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.Response;
import lkd.namsic.cnkb.enums.LogType;
import lkd.namsic.cnkb.exception.StatusException;
import lkd.namsic.cnkb.repository.GameLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.common.value.qual.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.Callable;

@Slf4j
@Component
public class Config {
    
    private static Config instance;
    public static Config getInstance() { return instance; }
    
    private final GameLogRepository gameLogRepository;

    @NonNull
    public final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.KOREA);

    public final String VERSION = "Beta 1.0.0";
    public final int MAX_PLAYER_COUNT = 5;
    public final String REGEX = "[^A-Za-z-_0-9ㄱ-ㅣ가-힣~!@#$%^&*=+/,. ]";
    public final List<String> INVALID_WORD_LIST;
    
    private final Random random = new Random();
    
    @PostConstruct
    public void init() {
        instance = this;
    }

    public Config(
        @Autowired GameLogRepository gameLogRepository
    ) throws Exception {
        this.gameLogRepository = gameLogRepository;
        
        INVALID_WORD_LIST = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(System.getenv("invalid")));

        String line;
        while((line = reader.readLine()) != null) {
            INVALID_WORD_LIST.add(line);
        }

        reader.close();
    }

    @SuppressWarnings("unchecked")
    public void checkRole(@NonNull HttpServletRequest request, String... requiredRoles) {
        List<String> roleSet = (List<String>) request.getAttribute("roles");

        if(roleSet == null) {
            throw new StatusException(403, "Forbidden resource");
        }

        for(String requiredRole : requiredRoles) {
            if(!roleSet.contains(requiredRole)) {
                throw new StatusException(401, "Unauthorized");
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Response> T safeCall(@NonNull String methodName, @NonNull Callable<T> callable) {
        try {
            return callable.call();
        } catch(StatusException e) {
            return (T) T.builder()
                .status(e.getStatus())
                .message(e.getMessage())
                .build();
        } catch(Exception e) {
            log.error(methodName, e);

            return (T) T.builder()
                .status(500)
                .message("Unknown Error")
                .build();
        }
    }

    public String getIp(@NonNull HttpServletRequest request) {
        String ip;

        ip = request.getHeader("X-Forwarded-For");
        if(ip == null) ip = request.getHeader("Proxy-Client-IP");
        if(ip == null) ip = request.getHeader("WL-Proxy-Client-IP");
        if(ip == null) ip = request.getHeader("HTTP_CLIENT_IP");
        if(ip == null) ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if(ip == null) ip = request.getRemoteAddr();

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

    @NonNull
    public <T extends Response> ResponseEntity<T> getResponseEntity(@NonNull T response) {
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
    
    public void log(@NonNull LogType logType, @Nullable Player player, @NonNull String log) {
        gameLogRepository.save(
            GameLog.builder()
                .player(player)
                .logType(logType.value)
                .log(log)
                .build()
        );
    }
    
    public void error(@NonNull String message, @NonNull Throwable t) {
        gameLogRepository.save(
            GameLog.builder()
                .logType(LogType.ERROR.value)
                .log(String.valueOf(t.getMessage()))
                .build()
        );
        log.error(message, t);
    }
    
    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public int getRandom(int min, @IntRange(from = 1) int range) {
        return this.random.nextInt(range) + min;
    }
    
    public long getRandom(int min, @IntRange(from = 1) long range) {
        return this.random.nextLong(range) + min;
    }
    
    public double getRandom() {
        return this.random.nextDouble();
    }

}