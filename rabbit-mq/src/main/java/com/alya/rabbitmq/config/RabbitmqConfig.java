package com.alya.rabbitmq.config;

import com.alya.rabbitmq.constant.Constant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 */
@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue queueOne() {
        return new Queue(Constant.QUEUE_ONE);
    }

    @Bean
    public Queue queueTwo() {
        return new Queue(Constant.QUEUE_TWO);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(Constant.FANOUT_EXCHANGE);
    }

    @Bean
    public Binding bindingOne() {
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingTwo() {
        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
    }

}
