package com.margin.model.ukraine.registry.scraper;

import com.margin.XMLSplitter;
import com.margin.context.ComponentProvider;
import com.margin.enums.FileType;
import com.margin.enums.Status;
import com.margin.error.ApiException;
import com.margin.SourceDownloaderComponent;
import com.margin.scraper.GenericScrapedUnit;
import com.margin.scraper.GenericScrapingSource;
import com.margin.scraper.Scraper;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.springframework.data.mongodb.core.mapping.Document;
import rx.Observable;
import rx.Subscriber;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.util.Assert.notNull;


@Document(collection = "uk_registry_source")
@Getter
@Setter
public class UA_RegistryScraper implements Scraper<GenericScrapingSource<String>, GenericScrapingSource<String>, GenericScrapedUnit<String>> {

    @Override
    public void scrap(final GenericScrapingSource<String> source, final Subscriber<GenericScrapedUnit<String>> subscriber) {
        notNull(source, "Source for scraping can not be null.");
        notNull(subscriber, "Subscriber for document for scraping can not be null.");
        final String url = source.getSource();
        final XMLSplitter splitter = ComponentProvider.getBean(XMLSplitter.class);
        final SourceDownloaderComponent downloader = ComponentProvider.getBean(SourceDownloaderComponent.class);
        final long[] counter = {0};
        final Observable<GenericScrapedUnit<String>> myObservable = Observable.unsafeCreate(innerSubscriber -> {
            try {
                Subscriber<String> subscriberStr = new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        subscriber.onError(throwable);
                    }

                    @Override
                    public void onNext(String s) {
                        ++counter[0];
                        GenericScrapedUnit<String> scrapingSource = new GenericScrapedUnit<>();
                        scrapingSource.setDocument(s);
                        scrapingSource.setFileType(FileType.XML_STRING);
                        scrapingSource.setUrl(url);
                        scrapingSource.setScrapDate(LocalDateTime.now());
                        scrapingSource.setStatus(Status.SUCCESS);
                        scrapingSource.setIndex(counter[0]);
                        subscriber.onNext(scrapingSource);
                    }
                };

//                Optional<Path> path = downloader.downloadRegistryFile(url, "UO.xml");t
                Optional<Path> path = downloader.downloadRegistryFile(url, "10mb.mp4");
                if (path.isPresent())
                    splitter.split(path.get().toAbsolutePath().toString(), "RECORD", "windows-1251", subscriberStr);
                else
                    subscriber.onError(new ApiException("Source file is not available!"));

            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
        myObservable.subscribe(subscriber);
    }

    @Override
    public void scrapPage(final GenericScrapingSource<String> source, final Subscriber<GenericScrapedUnit<String>> subscriber) {
        notNull(source, "Source can not be null");
        final String pageURL = source.getSource();
        notNull(source, "Page URL can not be null");
        final Observable<GenericScrapedUnit<String>> pageObservable = Observable.unsafeCreate(innerSubscriber -> {
            final GenericScrapedUnit<org.jsoup.nodes.Document> scrapedUnit = new GenericScrapedUnit<>();
            final String userAgent = Jsoup.connect("http://www.google.com").request().header("User-Agent");
            try {

            } catch (Exception e) {

            }
            innerSubscriber.onCompleted();
        });
        pageObservable.subscribe(subscriber);
    }

}
