package com.pl.socketioserver.config;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.listener.DataListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIOConfigure {

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("10.16.10.62");
        config.setPort(9092);

        final SocketIOServer server = new SocketIOServer(config);
        server.addNamespace("admin");
        server.addNamespace("client");
        server.addNamespace("passengers");

        server.start();
        return server;
    }

    @Bean
    public SpringAnnotationScanner socketIOSpringAnnotationScanner(SocketIOServer socketIOServer){
        return new SpringAnnotationScanner(socketIOServer);
    }
}
