package com.margin.scraper.api;

import com.margin.scraper.InfoResource;
import com.margin.scraper.ScraperLoadingResource;
import com.margin.scraper.ScraperSourceResource;

public interface ScraperApiClient {
    InfoResource info();
    ScraperSourceResource scraperSource();
    ScraperLoadingResource loader();
}
