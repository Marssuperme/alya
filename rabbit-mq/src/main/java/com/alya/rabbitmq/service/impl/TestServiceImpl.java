package com.alya.rabbitmq.service.impl;

import com.alya.rabbitmq.constant.Constant;
import com.alya.rabbitmq.service.ITestService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iTestService")
public class TestServiceImpl implements ITestService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TestServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(Constant.FANOUT_EXCHANGE,"", "This is a message! Tag: " + i);
        }
        System.out.println("Message send!");
    }
}
