package com.margin.listener;

import com.margin.scraper.api.ScraperApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {
    @Autowired
    private ScraperApiClient scraperApiClient;

    @EventListener({ContextRefreshedEvent.class})
    public void onContextRefreshedEvent() {
//        final GenericListResponseDTO<ScraperSourceDTO> all = scraperApiClient.scraperSource().findAll();
//        final GenericListResponseDTO<SourceMetaDataDTO> allMetaData = scraperApiClient.scraperSource().findAllMetaData();
    }

}
