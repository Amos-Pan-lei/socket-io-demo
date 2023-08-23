package com.pl.socketioserver.service;

import com.corundumstudio.socketio.SocketIOServer;
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
}
