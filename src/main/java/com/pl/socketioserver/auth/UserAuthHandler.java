package com.pl.socketioserver.auth;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * 登录身份 鉴权拦截器
 */
@Slf4j
public class UserAuthHandler implements AuthorizationListener {

    private String authToken="U2FsdGVkX19o1hOU61gP6ywfwusxym/QWUR0E9SULuI=";
    @Override
    public boolean isAuthorized(HandshakeData data) {

        String hostAddress = data.getAddress().getAddress().getHostAddress();
        Map<String, List<String>> urlParams = data.getUrlParams();
        log.info(" ip {} 鉴权参数=  {}", hostAddress, urlParams);
        log.info(" ip {} handshake =  {}", hostAddress, JSONUtil.toJsonPrettyStr(data));

        return StrUtil.equals(authToken,data.getSingleUrlParam("authToken"));
    }
}
