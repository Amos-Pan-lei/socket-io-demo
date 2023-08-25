package com.pl.socketioserver.bean;

import lombok.Data;

@Data
public class ChatObj {
    private String ip;
    private String user;
    private String msg;
    private String userPic;
}
