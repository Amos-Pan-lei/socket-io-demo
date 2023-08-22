package com.pl.socketioserver.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class EventAware {

    @OnConnect
    public void onConnect(SocketIOClient socketIOClient) {
        log.info("客户端 {} 连接 ", socketIOClient.getSessionId());
        SocketIONamespace namespace = socketIOClient.getNamespace();

        log.info("namespace = {}",namespace.getName());
        socketIOClient.joinRoom("room001");
        socketIOClient.leaveRoom("");
    }

    @OnEvent("chatEvent")
    public void onChatEvent(SocketIOClient client, AckRequest ackSender, Object data) {
        log.info("客户端 {} 发送 chatEvent  , AckRequest {} , 数据 {}", client.getSessionId(), ackSender, data);
        client.getNamespace().getBroadcastOperations().sendEvent("chatEvent", new Random().doubles().toString());
        log.info("client {} 在 namespace {} 的 room = {}",client.getSessionId(),client.getNamespace().getName(),client.getAllRooms());
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient socketIOClient) {
        log.info("客户端 {} 断开连接 ", socketIOClient.getSessionId());

    }

}
