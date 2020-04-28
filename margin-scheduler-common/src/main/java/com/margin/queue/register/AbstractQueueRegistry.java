package com.margin.queue.register;

import com.margin.enums.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public abstract class AbstractQueueRegistry {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final String QUEUE_NAME_DELIMITER = ".";
    private static final boolean EXCHANGE_DURABLE = true;
    private static final boolean EXCHANGE_AUTO_DELETE = false;
    private static final boolean QUEUE_DURABLE = true;
    private static final boolean QUEUE_EXCLUSIVE = false;
    private static final boolean QUEUE_AUTO_DELETE = false;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    private DirectExchange exchange;

    private Set<Queue> registeredQueues = new HashSet<>();

    public void init() {
        logger.trace("Registering Queues...");

        exchange = createExchange();

        final Set<Channel> channels = getChannels();

        channels.forEach(this::register);

        logger.trace("Done registering {} Queues.", channels.size());
    }

    public abstract String getExchangeName();

    public abstract String getQueueNamePrefix();

    public abstract Set<Channel> getChannels();

    protected Set<Queue> getRegisteredQueues() {
        return registeredQueues;
    }

    public DirectExchange createExchange() {
        final String exchangeName = getExchangeName();
        final DirectExchange directExchange = new DirectExchange(
                exchangeName,
                EXCHANGE_DURABLE,
                EXCHANGE_AUTO_DELETE
        );

        rabbitAdmin.declareExchange(directExchange);

        logger.trace("Done creating Exchange:'{}'.", exchangeName);
        return directExchange;
    }

    public void register(final Channel publicationChannel) {
        logger.trace("Registering a queue for Channel:{}...", publicationChannel);

        final Queue registeredQueue = register(
                resolveQueueName(publicationChannel),
                resolveRoutingKey(publicationChannel)
        );

        logger.trace("Done registering Queue:'{}' for Channel:{}.", registeredQueue.getName(), publicationChannel);
    }

    public Queue register(final String queueName, final String routingKey) {
        logger.trace("Registering Queue:'{}'... (routingKey:'{}')", queueName, routingKey);

        final Queue queue = createQueue(queueName);

        bind(queue, routingKey);

        registeredQueues.add(queue);

        logger.trace("Done registering Queue:'{}'.", queueName);
        return queue;
    }

    public String resolveQueueName(final Channel channel) {
        final String queueNamePrefix = getQueueNamePrefix();
        logger.trace("Resolving queue name for Channel:{}... (queueNamePrefix:{})", channel, queueNamePrefix);

        final String result = String.join(QUEUE_NAME_DELIMITER, queueNamePrefix, channel.name().toLowerCase());

        logger.trace("Done resolving queue name '{}' for Channel:{}.", result, channel);
        return result;
    }

    public Queue createQueue(final String queueName) {
        final Queue queue = new Queue(queueName, QUEUE_DURABLE, QUEUE_EXCLUSIVE, QUEUE_AUTO_DELETE);

        rabbitAdmin.declareQueue(queue);

        logger.trace("Done creating Queue:'{}'.", queueName);
        return queue;
    }

    public void bind(final Queue queue, final String routingKey) {
        doBind(queue, routingKey);
    }

    public String resolveRoutingKey(final Channel channel) {
        logger.trace("Resolving routing key for Channel:{}...", channel);

        final String result = channel.name().toLowerCase();

        logger.trace("Done resolving routing key '{}' for Channel:{}.", result, channel);
        return result;
    }

    public void doBind(final Queue queue, final String routingKey) {
        final Binding binding = BindingBuilder.bind(queue).to(exchange).with(routingKey);

        rabbitAdmin.declareBinding(binding);
    }
}
