package com.pl.socketioserver.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "socket.io")
public class SocketIOProperties {

    private int bossThreads = 0; // 0 = current_processors_amount * 2
    private int workerThreads = 0; // 0 = current_processors_amount * 2
    private boolean useLinuxNativeEpoll;

    private boolean allowCustomRequests = false;

    private int upgradeTimeout = 10000;
    private int pingTimeout = 60000;
    private int pingInterval = 25000;
    private int firstDataTimeout = 5000;

    private int maxHttpContentLength = 64 * 1024;
    private int maxFramePayloadLength = 64 * 1024;

    private String packagePrefix;
    private String hostname;
    private int port = -1;

    private String sslProtocol = "TLSv1";

    private String keyStoreFormat = "JKS";

    private String keyStorePassword;

    private String allowHeaders;

    private String trustStoreFormat = "JKS";

    private String trustStorePassword;



    private boolean preferDirectBuffer = true;
    private boolean addVersionHeader = true;

    private String origin;

    private boolean httpCompression = true;

    private boolean websocketCompression = true;

    private boolean randomSession = false;

    //证书校验
    private boolean needClientAuth = false;
}
