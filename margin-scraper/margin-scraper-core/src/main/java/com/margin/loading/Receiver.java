package com.margin.loading;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@RabbitListener(queues = "task")
public class Receiver {

    @Autowired
    private LoadingService loadingService;

	@RabbitHandler
    public void process(String interfaceName) {
        System.out.println();
    }
	
}
