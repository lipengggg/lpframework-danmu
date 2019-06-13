package com.lpframework.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 *
 * @author lipeng
 * @version Id: WebSocketConfig.java, v 0.1 2019/6/12 15:38 lipeng Exp $$
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/webSocketHandler");
        registry.addHandler(loginWebSocketHandler(), "/loginWebSocketHandler");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new WebSocketHandler();
    }

    @Bean
    public LoginWebSocketHandler loginWebSocketHandler() {
        return new LoginWebSocketHandler();
    }
}
