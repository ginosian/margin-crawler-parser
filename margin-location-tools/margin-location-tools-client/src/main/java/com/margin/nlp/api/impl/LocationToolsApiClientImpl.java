package com.margin.nlp.api.impl;

import com.margin.AbstractApiClient;
import com.margin.nlp.InfoResource;
import com.margin.nlp.api.LocationToolsApiClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LocationToolsApiClientImpl extends AbstractApiClient implements LocationToolsApiClient {

    //TODO replace this to config files
    private static String BASE_URL = "http://localhost:8083/location-tools/v1";

    private InfoResource infoResource;

    public LocationToolsApiClientImpl() {
        super(BASE_URL);
        infoResource = new InfoResource(getClient(), getWebTarget());
    }

    @Override
    public InfoResource info() {
        return infoResource;
    }

    @Override
    public void close() throws IOException {

    }
}
