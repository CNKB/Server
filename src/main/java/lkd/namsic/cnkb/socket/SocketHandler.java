package lkd.namsic.cnkb.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lkd.namsic.cnkb.service.SocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class SocketHandler extends TextWebSocketHandler {

    public static final Map<String, Long> sessionIdMap = new LinkedHashMap<>();
    public static final Map<Long, WebSocketSession> sessionMap = new LinkedHashMap<>();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SocketService socketService;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = session.getId();

        if(sessionIdMap.containsKey(sessionId)) {
            long playerId = sessionIdMap.remove(sessionId);
            sessionMap.remove(playerId);

            log.info("Removed non-disconnected session - {}", playerId);
        }

        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        SocketData.Input input;

        try {
            input = objectMapper.readValue(payload, SocketData.Input.class);
        } catch (JsonProcessingException e) {
            SocketData.Output output = SocketData.Output
                    .builder()
                    .status(400)
                    .message(e.getMessage())
                    .build();

            String outputString = objectMapper.writeValueAsString(output);
            session.sendMessage(new TextMessage(outputString));

            log.info("Session Parsing Error - {} {}", payload, e.getMessage());
            return;
        }

        SocketData.Output output = socketService.handleData(input, session);

        log.info("Session - {} {}", payload, output.status);
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(output)));
    }

}
