package com.lpframework.websocket;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * @author lipeng
 * @version Id: WebSocketHandler.java, v 0.1 2019/6/12 15:32 lipeng Exp $$
 */
public class WebSocketHandler extends TextWebSocketHandler {

    private static CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<WebSocketSession>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if(!CollectionUtils.isEmpty(sessions)){
            //遍历所有的客户端发送消息
            for (WebSocketSession socketSession: sessions){
                byte[] bytes = message.asBytes();
                TextMessage textMessage = new TextMessage("我发送的消息："+new String(bytes));
                socketSession.sendMessage(textMessage);
            }
        }
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
