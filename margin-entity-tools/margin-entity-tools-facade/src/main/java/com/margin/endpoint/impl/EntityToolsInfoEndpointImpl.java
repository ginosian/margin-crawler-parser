package com.margin.endpoint.impl;

import com.margin.dto.info.InfoDTO;
import com.margin.endpoint.EntityToolsInfoEndpoint;
import com.margin.repository.DummyElasticRepository;
import com.margin.repository.DummyMongoRepository;
import com.margin.repository.DummyPostgresRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EntityToolsInfoEndpointImpl implements EntityToolsInfoEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(EntityToolsInfoEndpointImpl.class);

    @Autowired
    private DummyMongoRepository dummyMongoRepository;

    @Autowired
    private DummyPostgresRepository dummyPostgresRepository;

    @Autowired
    private DummyElasticRepository dummyElasticRepository;

    @Override
    public InfoDTO get() {
        return new InfoDTO("This is Margin-ENTITY-TOOLS micro-service. It works just great");
    }

    @Override
    public InfoDTO post(final InfoDTO infoDTO) {
        return infoDTO;
    }
}
