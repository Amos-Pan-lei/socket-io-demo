package com.pl.socketioserver.service;

import com.corundumstudio.socketio.SocketIOServer;
import com.pl.socketioserver.bean.ChatObj;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocketIOManager {

    private final SocketIOServer socketIOServer;

    public void reFreshAllClients() {
        socketIOServer.getBroadcastOperations().sendEvent("refreshPage", 1);
    }

    public void noticeClients(ChatObj data){
        socketIOServer.getBroadcastOperations().sendEvent("barrageNotice", data);
    }
}
