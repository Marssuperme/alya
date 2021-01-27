package com.alya.system.user.app.controller;

import com.alya.system.user.app.socket.WebSocketServer;
import com.alya.web.api.ResponseResult;
import com.alya.web.api.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author alya
 */
@RequestMapping(value = "/user")
@RestController
public class UserController {

    private final WebSocketServer webSocketServer;

    @Autowired
    public UserController(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }

    @GetMapping(value = "/test")
    public void test(){
        System.out.println("success");
    }

    @RestResult
    @PostMapping("/push")
    public Object push(@RequestParam String userName) {
        webSocketServer.sendMessage(userName, "Hello, my name is alya~");
        return ResponseResult.success();
    }

    @GetMapping("index")
    public Object index() {
        return "index";
    }

}
