package com.margin.config;
/*
 *   @author ironman
 *   @since  11/14/18
 */

import com.margin.endpoint.impl.EntityToolsInfoEndpointImpl;
import com.margin.endpoint.impl.SourceVersioningEndpointImpl;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;


@Component
@ApplicationPath("/entity-tools/v1")
public class JerseyConfig extends GenericJerseyConfig {

    public JerseyConfig() {
        super();
        registerEndpoints();
    }

    private void registerEndpoints() {
        register(EntityToolsInfoEndpointImpl.class);
        register(SourceVersioningEndpointImpl.class);
    }
}