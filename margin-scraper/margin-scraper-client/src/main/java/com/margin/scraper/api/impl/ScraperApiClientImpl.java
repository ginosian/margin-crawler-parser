package com.margin.scraper.api.impl;

import com.margin.AbstractApiClient;
import com.margin.scraper.InfoResource;
import com.margin.scraper.ScraperLoadingResource;
import com.margin.scraper.ScraperSourceResource;
import com.margin.scraper.api.ScraperApiClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ScraperApiClientImpl extends AbstractApiClient implements ScraperApiClient {

    //TODO replace this to config files
    private static String BASE_URL = "http://localhost:8081/scraper/v1";

    private InfoResource infoResource;
    private ScraperSourceResource scraperSourceResource;
    private ScraperLoadingResource scraperLoadingResource;

    public ScraperApiClientImpl() {
        super(BASE_URL);
        infoResource = new InfoResource(getClient(), getWebTarget());
        scraperSourceResource = new ScraperSourceResource(getClient(), getWebTarget());
        scraperLoadingResource = new ScraperLoadingResource(getClient(), getWebTarget());
    }

    @Override
    public InfoResource info() {
        return infoResource;
    }

    @Override
    public ScraperSourceResource scraperSource() {
        return scraperSourceResource;
    }

    @Override
    public ScraperLoadingResource loader() {
        return this.scraperLoadingResource;
    }

    @Override
    public void close() throws IOException {

    }
}
