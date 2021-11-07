package lkd.namsic.cnkb.config;

import lkd.namsic.cnkb.controller.SocketHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Slf4j
@Configuration
@EnableWebSocket
@AllArgsConstructor
public class SocketConfig implements WebSocketConfigurer {
    
    private final SocketHandler socketHandler;
    
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        log.info("Registering socket interceptor");
        
        registry.addHandler(socketHandler, "/ws")
            .setAllowedOriginPatterns("*");
    }
    
}