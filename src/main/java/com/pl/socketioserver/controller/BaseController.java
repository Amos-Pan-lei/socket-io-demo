package com.pl.socketioserver.controller;

import com.pl.socketioserver.service.SocketIOManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class BaseController {


    private final SocketIOManager socketIOManager;

    @GetMapping(value = "/refreshAllClients")
    public String reFreshAllClients(){
         socketIOManager.reFreshAllClients();
        return "ok";
    }
}
