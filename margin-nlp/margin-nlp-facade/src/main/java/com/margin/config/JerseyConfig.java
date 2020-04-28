package com.margin.config;

import com.margin.endpoint.impl.NLPInfoEndpointImpl;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/nlp/v1")
public class JerseyConfig extends GenericJerseyConfig{

    public JerseyConfig() {
        super();
        registerEndpoints();
    }

    private void registerEndpoints() {
        register(NLPInfoEndpointImpl.class);
    }
}
