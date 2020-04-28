package com.margin.nlp.api.impl;

import com.margin.AbstractApiClient;
import com.margin.nlp.InfoResource;
import com.margin.nlp.api.NLPApiClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NLPApiClientImpl  extends AbstractApiClient implements NLPApiClient {

    //TODO replace this to config files
    private static String BASE_URL = "http://localhost:8082/nlp/v1";

    private InfoResource infoResource;

    public NLPApiClientImpl() {
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
