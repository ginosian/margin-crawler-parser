package com.margin.queue.register;

import com.margin.enums.Channel;
import com.margin.queue.listener.SingleChannelLoadingListenerImpl;
import com.margin.event.loading.processor.SingleChannelLoadingProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PerChannelQueueRegistry {

    private static final Logger logger = LoggerFactory.getLogger(PerChannelQueueRegistry.class);

    private String exchange = "loader";

    private String queueNamePrefix = "loading";

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private SingleChannelLoadingProcessor processor;

    @Autowired
    private ConnectionFactory rabbitConnectionFactory;

//    @Qualifier("amqpMessageConverter")
//    @Autowired
//    private MessageConverter messageConverter;

    @Autowired
    private ApplicationContext appContext;

    private final Set<MessageListenerContainer> listenerContainers = new HashSet<>();

    @PostConstruct
    public void init() {
        logger.info("Registering submission listeners for channels.");

        Set<Channel> supportedChannels = Arrays.stream(Channel.values())
                .filter(Channel::isActive)
                .collect(Collectors.toSet());

        supportedChannels.forEach(publicationChannel -> {
            logger.info("Registering listener for channel:{}.", publicationChannel);

            Queue queue = new Queue(queueNamePrefix + "." + publicationChannel.toString().toLowerCase(), true, false, false);
            DirectExchange directExchange = new DirectExchange(exchange, true, false);

            Binding binding = BindingBuilder.bind(queue).to(directExchange).with(publicationChannel.toString().toLowerCase());

            rabbitAdmin.declareQueue(queue);
            rabbitAdmin.declareExchange(directExchange);
            rabbitAdmin.declareBinding(binding);

            SingleChannelLoadingListenerImpl listener = appContext.getBean(SingleChannelLoadingListenerImpl.class);
            listener.initialize(processor);

            MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(listener, "process");
//            listenerAdapter.setMessageConverter(messageConverter);

            SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(rabbitConnectionFactory);

            listenerContainer.addQueues(queue);
            listenerContainer.setRabbitAdmin(rabbitAdmin);
            listenerContainer.setMaxConcurrentConsumers(processor.getMaxConcurrentConsumers());
            listenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
//            listenerContainer.setMessageConverter(messageConverter);

            listenerContainer.setMessageListener(listenerAdapter);

            listenerContainer.start();

            listenerContainers.add(listenerContainer);
        });

        logger.info("Started {} listeners.", listenerContainers.size());
    }

    @PreDestroy
    public void stopListeners() {
        logger.info("Stopping all single channel listeners...");

        listenerContainers.forEach(Lifecycle::stop);

        logger.info("All listeners stopped.");
    }
}
