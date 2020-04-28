package com.margin.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitTemplateHelper {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void convertAndSendOptional(final String exchange, final String routingKey, final Object object) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, object);
        } catch (AmqpException e) {
            logger.error("Can't enqueue message", e);
        }
    }
}
