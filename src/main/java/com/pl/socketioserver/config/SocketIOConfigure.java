package com.pl.socketioserver.config;

import cn.hutool.log.Log;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.pl.socketioserver.auth.UserAuthHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

@EnableConfigurationProperties(SocketIOProperties.class)
@Configuration
@RequiredArgsConstructor
@Slf4j
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


        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            log.error("本机地址报错了",e);
        }
        log.info("本机 == {} ",localHost);

        server.start();
        return server;
    }

    @Bean
    public SpringAnnotationScanner socketIOSpringAnnotationScanner(SocketIOServer socketIOServer) {
        return new SpringAnnotationScanner(socketIOServer);
    }
}
