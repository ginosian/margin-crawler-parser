package com.margin.config;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Bean
    public RabbitAdmin rabbitAdmin(){
        return new RabbitAdmin(rabbitTemplate);
    }
}
