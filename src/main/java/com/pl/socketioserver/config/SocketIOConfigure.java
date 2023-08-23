package com.pl.socketioserver.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.pl.socketioserver.auth.UserAuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(SocketIOProperties.class)
@Configuration
@RequiredArgsConstructor
public class SocketIOConfigure {
    private final SocketIOProperties properties;
    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(properties.getHostname());
        config.setPort(properties.getPort());
        config.setOrigin(properties.getOrigin());

        config.setUpgradeTimeout(properties.getUpgradeTimeout());
        config.setAllowCustomRequests(properties.isAllowCustomRequests());
        config.setUpgradeTimeout(properties.getUpgradeTimeout());
        config.setPingTimeout(properties.getPingTimeout());
        config.setPingInterval(properties.getPingInterval());
        config.setKeyStorePassword(properties.getKeyStorePassword());
        config.setAllowHeaders(properties.getAllowHeaders());
        config.setTrustStorePassword(properties.getTrustStorePassword());
        config.setHttpCompression(properties.isHttpCompression());
        config.setWebsocketCompression(properties.isWebsocketCompression());
        config.setRandomSession(properties.isRandomSession());
        config.setNeedClientAuth(properties.isNeedClientAuth());


        config.setAuthorizationListener(new UserAuthHandler());
        final SocketIOServer server = new SocketIOServer(config);
        server.start();

        return server;
    }

    @Bean
    public SpringAnnotationScanner socketIOSpringAnnotationScanner(SocketIOServer socketIOServer) {
        return new SpringAnnotationScanner(socketIOServer);
    }
}
