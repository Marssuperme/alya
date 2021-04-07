package com.alya.rabbitmq.controller;

import com.alya.rabbitmq.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final ITestService iTestService;

    @Autowired
    public TestController(ITestService iTestService) {
        this.iTestService = iTestService;
    }

    @PostMapping(value = "queue-one/send")
    public void send() {
        iTestService.send();
    }

}
