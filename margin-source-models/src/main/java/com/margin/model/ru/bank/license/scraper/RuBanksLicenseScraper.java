package com.margin.model.ru.bank.license.scraper;

import com.margin.enums.LoadingStage;
import com.margin.error.ApiException;
import com.margin.scraper.GenericScrapedUnit;
import com.margin.scraper.GenericScrapingSource;
import com.margin.scraper.Scraper;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rx.Observable;
import rx.Subscriber;

import static org.springframework.util.Assert.notNull;

public class RuBanksLicenseScraper implements Scraper<GenericScrapingSource<String>, GenericScrapingSource<String>, GenericScrapedUnit<Document>> {

    @Override
    public void scrap(final GenericScrapingSource<String> source, final Subscriber<GenericScrapedUnit<Document>> subscriber) {
        notNull(source, "Source for scraping can not be null.");
        notNull(subscriber, "Subscriber for document for scraping can not be null.");
        final String url = source.getSource();
        final Observable<GenericScrapedUnit<Document>> myObservable = Observable.unsafeCreate(innerSubscriber -> {
            Document mainPageDocument = null;
            Elements companyRows = null;
            final String userAgent = Jsoup.connect("http://www.google.com").request().header("User-Agent");
            try {
                mainPageDocument = getDocument(url, userAgent);
                companyRows = mainPageDocument.select("table.data tbody tr");
            } catch (Exception e) {
                innerSubscriber.onError(e);
            }
            int size = companyRows.size();
            //TODO remove this
            size = 16;
            for (int i = 0; i < size; i++) {
                Element cr = companyRows.get(i);
                final GenericScrapedUnit<Document> scrapedUnit = new GenericScrapedUnit<>();
                scrapedUnit.setIndex((long) i);
                scrapedUnit.setContext("Row count");
                scrapedUnit.setUrl(url);
                try {
                    final String pageUrl = get(cr);
                    Document document = getDocument(pageUrl, userAgent);
                    scrapedUnit.setDocument(document);
                    scrapedUnit.setUrl(pageUrl);
                    innerSubscriber.onNext(scrapedUnit);
                } catch (Exception e) {
                    final ApiException exception = new ApiException(e.getMessage());
                    exception.setStage(LoadingStage.SCRAPING);
                    scrapedUnit.setApiException(exception);
                    innerSubscriber.onNext(scrapedUnit);
                }
            }
            innerSubscriber.onCompleted();
        });
        myObservable.subscribe(subscriber);
    }

    @Override
    public void scrapPage(final GenericScrapingSource<String> source, final Subscriber<GenericScrapedUnit<Document>> subscriber) {
        notNull(source, "Source can not be null");
        final String pageURL = source.getSource();
        notNull(source, "Page URL can not be null");
        final Observable<GenericScrapedUnit<Document>> pageObservable = Observable.unsafeCreate(innerSubscriber -> {
            final GenericScrapedUnit<Document> scrapedUnit = new GenericScrapedUnit<>();
            final String userAgent = Jsoup.connect("http://www.google.com").request().header("User-Agent");
            try {
                Document document = getDocument(pageURL, userAgent);
                scrapedUnit.setDocument(document);
                scrapedUnit.setUrl(pageURL);
                innerSubscriber.onNext(scrapedUnit);
            } catch (Exception e) {
                final ApiException exception = new ApiException(e.getMessage());
                exception.setStage(LoadingStage.SCRAPING);
                scrapedUnit.setApiException(exception);
                innerSubscriber.onNext(scrapedUnit);
            }
            innerSubscriber.onCompleted();
        });
        pageObservable.subscribe(subscriber);
    }

    public String get(final Element cr) {
        Elements companyCells = cr.getElementsByTag("td");
        if (companyCells.size() != 5) {
            final ApiException exception =  new ApiException("Organization cell size should be 5");
            exception.setStage(LoadingStage.SCRAPING);
            throw exception;
        }
        return companyCells.get(2).getElementsByTag("a").get(0).absUrl("href");
    }

    private Document getDocument(final String url, final String userAgent){
        notNull(url, "url can n ot be null");

        try {
            if(StringUtils.isNotEmpty(userAgent)){
                return Jsoup.connect(url)
                        .userAgent(userAgent)
                        .timeout(30000)
                        .referrer("http://www.google.com").followRedirects(true).ignoreContentType(true)
                        .get();
            } else {
                return Jsoup.connect(url).get();
            }
//            return Jsoup.connect(url)
//                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
//                    .timeout(30000)
//                    .referrer("http://www.google.com").followRedirects(true).ignoreContentType(true)
//                    .get();
        } catch (Exception e){
            throw new ApiException(e.getMessage());
        }
    }

}
