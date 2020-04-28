package com.margin.endpoint.impl;

import com.margin.dto.info.InfoDTO;
import com.margin.endpoint.LocationToolsInfoEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocationToolsInfoEndpointImpl implements LocationToolsInfoEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(LocationToolsInfoEndpointImpl.class);

    @Override
    public InfoDTO get() {
        return new InfoDTO("This is Margin-LOCATION-TOOLS micro-service. It works just great");
    }
}
