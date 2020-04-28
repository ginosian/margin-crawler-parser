package com.margin.config;
/*
 *   @author ironman
 *   @since  11/14/18
 */

import com.margin.endpoint.impl.LocationToolsInfoEndpointImpl;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;


@Component
@ApplicationPath("/location-tools/v1")
public class JerseyConfig extends GenericJerseyConfig{

    public JerseyConfig() {
        super();
        registerEndpoints();
    }

    private void registerEndpoints() {
        register(LocationToolsInfoEndpointImpl.class);
    }
}
