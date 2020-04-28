package com.margin.tmp.loader.api.impl;

import com.margin.AbstractApiClient;
import com.margin.tmp.loader.InfoResource;
import com.margin.tmp.loader.TmpLoaderApiResource;
import com.margin.tmp.loader.api.TmpLoaderApiClient;
import org.springframework.stereotype.Service;

@Service
public class TmpLoaderApiClientImpl extends AbstractApiClient implements TmpLoaderApiClient {
    //TODO replace this to config files
    private static String BASE_URL = "http://localhost:8089/tmp-loader/v1";

    private InfoResource infoResource;
    private TmpLoaderApiResource tmpLoaderApiResource;

    public TmpLoaderApiClientImpl() {
        super(BASE_URL);
        infoResource = new InfoResource(getClient(), getWebTarget());
        tmpLoaderApiResource = new TmpLoaderApiResource(getClient(), getWebTarget());
    }

    @Override
    public TmpLoaderApiResource tmpLoader() {
        return tmpLoaderApiResource;
    }

    @Override
    public InfoResource info() {
        return infoResource;
    }
}
