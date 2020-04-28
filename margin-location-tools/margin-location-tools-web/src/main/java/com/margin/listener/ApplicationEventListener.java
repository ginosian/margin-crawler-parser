package com.margin.listener;

import com.margin.enums.Country;
import com.margin.service.LocationService;
import com.margin.service.impl.LocationServiceImpl;
import com.margin.service.model.CountryCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {


    @Autowired
    private LocationService locationService;

    @EventListener({ContextRefreshedEvent.class})
    public void onContextRefreshedEvent() {
        System.out.println("hello there");
    }

}
