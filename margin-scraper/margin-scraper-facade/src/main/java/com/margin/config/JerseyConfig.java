package com.margin.config;

import com.margin.endpoint.impl.ScraperInfoEndpointImpl;
import com.margin.endpoint.impl.ScraperLoadingEndpointImpl;
import com.margin.endpoint.impl.ScraperSourceEndpointImpl;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;


@Component
@ApplicationPath("/scraper/v1")
public class JerseyConfig extends GenericJerseyConfig{

    public JerseyConfig() {
        super();
        registerEndpoints();
    }

    private void registerEndpoints() {
        register(ScraperInfoEndpointImpl.class);
        register(ScraperSourceEndpointImpl.class);
        register(ScraperLoadingEndpointImpl.class);
    }
}
