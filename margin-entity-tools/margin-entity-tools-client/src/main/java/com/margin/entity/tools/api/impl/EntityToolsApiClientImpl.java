package com.margin.entity.tools.api.impl;

import com.margin.AbstractApiClient;
import com.margin.entity.tools.InfoResource;
import com.margin.entity.tools.SourceVersioningResource;
import com.margin.entity.tools.api.EntityToolsApiClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EntityToolsApiClientImpl extends AbstractApiClient implements EntityToolsApiClient {

    //TODO replace this to config files
    private static String BASE_URL = "http://localhost:8084/entity-tools/v1";

    private InfoResource infoResource;
    private SourceVersioningResource sourceVersioningResource;


    public EntityToolsApiClientImpl() {
        super(BASE_URL);
        infoResource = new InfoResource(getClient(), getWebTarget());
        sourceVersioningResource = new SourceVersioningResource(getClient(), getWebTarget());
    }

    @Override
    public InfoResource info() {
        return infoResource;
    }

    @Override
    public SourceVersioningResource sourceVersioning() {
        return sourceVersioningResource;
    }

    @Override
    public void close() throws IOException {

    }
}
