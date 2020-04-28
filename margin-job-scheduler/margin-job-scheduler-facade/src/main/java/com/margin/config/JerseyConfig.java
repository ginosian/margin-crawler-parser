package com.margin.config;
/*
 *   @author ironman
 *   @since  11/14/18
 */

import com.margin.endpoint.impl.JobSchedulerEndpointImpl;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;


@Component
@ApplicationPath("/scheduler/v1")
public class JerseyConfig extends GenericJerseyConfig{

    public JerseyConfig() {
        super();
        registerEndpoints();
    }

    private void registerEndpoints() {
        register(JobSchedulerEndpointImpl.class);
    }
}
