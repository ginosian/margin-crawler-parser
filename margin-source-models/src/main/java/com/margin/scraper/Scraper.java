package com.margin.scraper;

import rx.Subscriber;

public interface Scraper<Source extends GenericScrapingSource, PageSource extends GenericScrapingSource, Result extends GenericScrapedUnit> {
    void scrap(Source source, Subscriber<Result> subscriber);
    void scrapPage(PageSource pageSource, Subscriber<Result> subscriber);
}