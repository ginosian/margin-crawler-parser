package com.margin.config;

import com.margin.mq.Sender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	@Bean
    public Queue queue() {
        return new Queue("task");
    }
	
	@Bean
	public Sender sender(){
		return new Sender();
	}
	
//	@Bean
//	public Receiver receiver(){
//		return new Receiver();
//	}
	
}
