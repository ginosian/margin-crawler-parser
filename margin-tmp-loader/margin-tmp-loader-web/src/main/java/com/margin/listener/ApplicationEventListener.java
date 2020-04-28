package com.margin.listener;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {
    @EventListener({ContextRefreshedEvent.class})
    public void onContextRefreshedEvent() {
        System.out.println();
    }
}
