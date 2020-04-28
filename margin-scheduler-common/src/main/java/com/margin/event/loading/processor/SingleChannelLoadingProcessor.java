package com.margin.event.loading.processor;

import com.margin.event.loading.message.SingleChannelLoadingQueueMessage;
import com.margin.scraper.api.ScraperApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.notNull;


@Service
public class SingleChannelLoadingProcessor {


    @Autowired
    private ScraperApiClient scraperApiClient;

    public void process(final SingleChannelLoadingQueueMessage event){
        notNull(event, "event can not be null");
        final Long scrapingSourceId = event.getScrapingSourceId();
        notNull(scrapingSourceId, "ScrapingSourceId can not be null.");
        scraperApiClient.loader().load(event.getScrapingSourceId());
    }

    public int getMaxConcurrentConsumers(){
        return 1;
    }
}
