package com.margin.config;

import com.margin.endpoint.impl.OldLoaderInfoEndpointImpl;
import com.margin.endpoint.impl.OldStyleDBLoaderEndpointImpl;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;


@Component
@ApplicationPath("/tmp-loader/v1")
public class JerseyConfig extends GenericJerseyConfig {

    public JerseyConfig() {
        super();
        registerEndpoints();
    }

    private void registerEndpoints() {
        register(OldStyleDBLoaderEndpointImpl.class);
        register(OldLoaderInfoEndpointImpl.class);
    }
}
