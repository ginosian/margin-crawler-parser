package com.margin.endpoint.impl;

import com.margin.dto.info.InfoDTO;
import com.margin.endpoint.ScraperInfoEndpoint;
import com.margin.entity.tools.api.EntityToolsApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ScraperInfoEndpointImpl implements ScraperInfoEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(ScraperInfoEndpointImpl.class);

    @Autowired
    private EntityToolsApiClient entityToolsApiClient;

    @Override
    public InfoDTO get() {
        entityToolsApiClient.info().info();
        return new InfoDTO("This is Margin-SCRAPER micro-service. It works just great");
    }
}
