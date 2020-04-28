package com.margin.endpoint.impl;

import com.margin.dto.info.InfoDTO;
import com.margin.endpoint.NLPInfoEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NLPInfoEndpointImpl implements NLPInfoEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(NLPInfoEndpointImpl.class);

    @Override
    public InfoDTO get() {
        return new InfoDTO("This is Margin-NLP micro-service. It works just great");
    }
}
