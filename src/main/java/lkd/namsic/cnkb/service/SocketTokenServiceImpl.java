package lkd.namsic.cnkb.service;

import lkd.namsic.cnkb.bearer.JwtTokenProvider;
import lkd.namsic.cnkb.exception.CommonException;
import lkd.namsic.cnkb.socket.SocketData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

@Slf4j
@Service
public class SocketTokenServiceImpl implements SocketTokenService {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    private void checkToken(@NonNull String accessToken) throws CommonException {
        long userId = jwtTokenProvider.validateToken(accessToken);

        if(userId == 0) {
            throw new CommonException(401, "Unauthorized");
        }
    }

    @Deprecated
    @Override
    public SocketData.Output handleData(@NonNull SocketData.Input input, @NonNull WebSocketSession session) {
        return SocketData.Output
                .builder()
                .status(404)
                .message("Deprecated")
                .build();
    }

    @Override
    public SocketData.Output handleData(@NonNull SocketData.Input input, @NonNull WebSocketSession session,
                                        @NonNull SocketTokenService service) throws CommonException {
        Map<String, Object> data = input.getData();

        if(data == null) {
            throw new CommonException(412, "Requires data");
        }

        String accessToken = (String) data.get("accessToken");
        checkToken(accessToken);

        return service.handleData(input, session);
    }

}
