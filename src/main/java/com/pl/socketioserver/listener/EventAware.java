package com.pl.socketioserver.listener;

import cn.hutool.core.util.StrUtil;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.pl.socketioserver.bean.ChatObj;
import com.pl.socketioserver.service.SocketIOManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventAware {

    private final SocketIOServer socketIOServer;
    private final SocketIOManager socketIOManager;

    @OnConnect
    public void onConnect(SocketIOClient client) {
        InetSocketAddress remoteAddress = (InetSocketAddress) client.getRemoteAddress();
        String hostAddress = remoteAddress.getAddress().getHostAddress();
        log.info("ip {} 客户端 {} 连接 ", hostAddress, client.getSessionId());
        client.joinRoom("room001");
    }

    @OnEvent("chatEvent")
    public void onChatEvent(SocketIOClient client, AckRequest ackSender, ChatObj data) {
        InetSocketAddress remoteAddress = (InetSocketAddress) client.getRemoteAddress();
        String hostAddress = remoteAddress.getAddress().getHostAddress();
        log.info("ip {} 客户端 {} 发送 chatEvent  ,   数据 {}", hostAddress, client.getSessionId(), data);
        data.setIp(hostAddress);
        data.setUser(StrUtil.isEmpty(data.getUser())?hostAddress: data.getUser());
        socketIOServer.getBroadcastOperations().sendEvent("chatEvent", data);
        socketIOManager.noticeClients(data);
        log.info("client {}  的 room = {}", hostAddress, client.getAllRooms());
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        InetSocketAddress remoteAddress = (InetSocketAddress) client.getRemoteAddress();
        String hostAddress = remoteAddress.getAddress().getHostAddress();
        log.info("ip {} 客户端 {} 断开连接 ", hostAddress, client.getSessionId());
    }

    @OnEvent("checkIp")
    public void checkIp(SocketIOClient client, AckRequest ackSender, ChatObj data) {
        InetSocketAddress remoteAddress = (InetSocketAddress) client.getRemoteAddress();
        String hostAddress = remoteAddress.getAddress().getHostAddress();
        log.info("checkIp  {} 客户端 {} 发送 checkIp  ", hostAddress, client.getSessionId());
        data.setIp(hostAddress);
        client.sendEvent("checkIp", data);
    }


}
