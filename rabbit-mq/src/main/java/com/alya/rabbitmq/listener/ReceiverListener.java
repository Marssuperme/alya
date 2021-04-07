package com.alya.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiverListener {

    @RabbitListener(queues = {"queue_one"})
    public void receiveOne(String msg, Channel channel, Message message) {
        System.out.println(channel.toString());
        System.out.println(message.toString());
        System.out.println("Queue one receive: " + msg);
    }

    @RabbitListener(queues = {"queue_two"})
    public void receiveTwo(String msg) {
        System.out.println("Queue two receive: " + msg);
    }

}
