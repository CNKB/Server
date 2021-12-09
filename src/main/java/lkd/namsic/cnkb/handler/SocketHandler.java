package lkd.namsic.cnkb.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lkd.namsic.cnkb.controller.SocketController;
import lkd.namsic.cnkb.dto.socket.SocketRequest;
import lkd.namsic.cnkb.dto.socket.SocketResponse;
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
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    private final SocketController socketController;

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
    protected void handleTextMessage(WebSocketSession session, @NonNull TextMessage message) {
        String payload = message.getPayload();
        SocketRequest request;
        
        try {
            request = objectMapper.readValue(payload, SocketRequest.class);
        } catch(JsonProcessingException e) {
            sendMessage(
                session,
                SocketResponse.builder()
                    .status(400)
                    .message(e.getMessage())
                    .build()
            );

            log.error("Error while parsing message - " + payload, e);
            return;
        }
        
        socketController.executeService(request, session);
    }
    
    public static void sendMessage(@NonNull WebSocketSession session,
                                   @NonNull SocketResponse output) {
        try {
            if(session.isOpen()) {
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(output)));
            }
        } catch(Exception e) {
            log.error("Error while sending message", e);
        }
    }

}