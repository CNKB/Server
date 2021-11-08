package lkd.namsic.cnkb.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lkd.namsic.cnkb.controller.SocketDispatcher;
import lkd.namsic.cnkb.dto.socket.SocketInput;
import lkd.namsic.cnkb.dto.socket.SocketOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class SocketHandler extends TextWebSocketHandler {

    public static final Map<String, Long> sessionMap = new ConcurrentHashMap<>();

    private final ObjectMapper objectMapper;
    private final SocketDispatcher socketDispatcher;

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = session.getId();

        Long playerId = sessionMap.remove(sessionId);
        if(playerId != null) {
            log.info("Removed non-disconnected session - {}", playerId);
        }

        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, @NonNull TextMessage message) throws Exception {
        String payload = message.getPayload();
        SocketInput input;

        try {
            input = objectMapper.readValue(payload, SocketInput.class);
        } catch(JsonProcessingException e) {
            SocketOutput output = SocketOutput.builder()
                .status(400)
                .message(e.getMessage())
                .build();

            String outputString = objectMapper.writeValueAsString(output);
            session.sendMessage(new TextMessage(outputString));

            log.info("Session Parsing Error - {} {}", payload, e.getMessage());
            return;
        }

        SocketOutput output = socketDispatcher.executeService(input, session);

        log.info("Session - {} {} {} {}", output.getStatus(), input.getPlayerId(), input.getRequest(), output.getMessage());
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(output)));

        if(output.getStatus() == -1) {
            session.close();
        }
    }

}