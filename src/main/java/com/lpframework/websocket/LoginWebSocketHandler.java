package com.lpframework.websocket;

import com.lpframework.chat.User;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * @author lipeng
 * @version Id: LoginWebSocketHandler.java, v 0.1 2019/6/12 15:32 lipeng Exp $$
 */
public class LoginWebSocketHandler extends TextWebSocketHandler {

    private static CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<WebSocketSession>();
    private static ConcurrentHashMap<String,User> userInfo = new ConcurrentHashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    }

    /**
     * 建立连接后触发的回调
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    /**
     * 收到消息时触发的回调
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        sessions.add(session);
        super.handleMessage(session, message);
    }

    /**
     * 传输消息出错时触发的回调
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        sessions.remove(session);
        System.out.println("客户端异常");
        super.handleTransportError(session, exception);
    }

    /**
     * 断开连接后触发的回调
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        super.afterConnectionClosed(session, status);
        System.out.println("客户端断开连接");
    }

    /**
     * 是否处理分片消息
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return super.supportsPartialMessages();
    }
}
