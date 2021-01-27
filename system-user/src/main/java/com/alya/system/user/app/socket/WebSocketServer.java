package com.alya.system.user.app.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author alya
 */
@ServerEndpoint(value = "/webSocket/{sid}", subprotocols = {"protocol"})
@Component("webSocketServer")
public class WebSocketServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);

    private static final AtomicInteger ONLINE = new AtomicInteger();

    private static final Map<String, Session> SESSION_MAP = new ConcurrentHashMap<>();


    public void sendMessage(Session session, String message) {
        if (Objects.isNull(session)) {
            return;
        }
        try {
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String userName, String message) {
        sendMessage(SESSION_MAP.get(userName), message);
    }

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sid") String userName) {
        SESSION_MAP.put(userName, session);
        addOnlineCount();
        LOGGER.info("用户：{}已连接！", userName);
        sendMessage(session, "欢迎" + userName + "加入连接！");
    }

    @OnClose
    public void onClose(@PathParam(value = "sid") String userName) {
        SESSION_MAP.remove(userName);
        subOnlineCount();
        LOGGER.info("用户：{}断开连接！", userName);
    }

    @OnMessage
    public void onMessage(String message) {
        message = "客户端：" + message + ",已收到";
        for (Session session : SESSION_MAP.values()) {
            sendMessage(session, message);
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        LOGGER.error("连接websocket错误！");
        throwable.printStackTrace();
    }

    public static void addOnlineCount() {
        ONLINE.incrementAndGet();
    }

    public static void subOnlineCount() {
        ONLINE.decrementAndGet();
    }

}
