package com.margin.queue.listener;


import com.margin.event.loading.processor.SingleChannelLoadingProcessor;
import com.margin.event.loading.message.SingleChannelLoadingQueueMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.Assert.state;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service
public class SingleChannelLoadingListenerImpl {

    private static final Logger logger = LoggerFactory.getLogger(SingleChannelLoadingListenerImpl.class);

    private SingleChannelLoadingProcessor processor;

    private final ConcurrentHashMap<String, LocalDateTime> requestQuotaLimiters = new ConcurrentHashMap<>();

    public void initialize(SingleChannelLoadingProcessor processor) {
        this.processor = processor;
    }

    public void process(final SingleChannelLoadingQueueMessage event) {
        state(processor != null, "Message listener has not yet been initialized");
        this.processor.process(event);
        notNull(event, "event cannot be null.");

    }

}

