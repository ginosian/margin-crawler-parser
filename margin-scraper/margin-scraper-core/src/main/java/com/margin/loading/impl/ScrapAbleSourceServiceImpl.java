package com.margin.loading.impl;

import com.margin.error.ApiException;
import com.margin.enums.LoadingStage;
import com.margin.dto.GenericResponseDTO;
import com.margin.dto.tmp.loader.LoaderDTO;
import com.margin.entity.tools.api.EntityToolsApiClient;
import com.margin.enums.Status;
import com.margin.loading.LoadingService;
import com.margin.loading.ModelResolver;
import com.margin.loading.facade.SourceInfoServiceFacade;
import com.margin.model.AbstractModel;
import com.margin.model.AbstractSource;
import com.margin.parser.GenericParsingSource;
import com.margin.parser.Parser;
import com.margin.scraper.GenericScrapedUnit;
import com.margin.scraper.GenericScrapingSource;
import com.margin.scraper.Scraper;
import com.margin.service.SourceService;
import com.margin.service.model.SourceMetaDataModel;
import com.margin.service.model.scrap_action_details.ScrapActionDetailsResponse;
import com.margin.service.model.scraped_unit_info.ScrapedUnitInfoResponse;
import com.margin.service.model.source_info.SourceInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Subscriber;

import static org.springframework.util.Assert.notNull;

@Service
public class ScrapAbleSourceServiceImpl implements LoadingService {
    private static final Logger logger = LoggerFactory.getLogger(ScrapAbleSourceServiceImpl.class);

    @Autowired
    private SourceService sourceService;

    @Autowired
    private ModelResolver modelResolver;

    @Autowired
    private EntityToolsApiClient entityToolsApiClient;

    @Autowired
    private SourceInfoServiceFacade infoServiceFacade;

    @Override
    public void load(final Long id) {
        notNull(id, "Scraping source id can not be null");
        final SourceInfoResponse scrapedSource = sourceService.get(id);
        final ScrapActionDetailsResponse info = infoServiceFacade.create(scrapedSource.getId(), scrapedSource.getDocumentType(), scrapedSource.getSourceUrl());
        final GenericResponseDTO<LoaderDTO> response = entityToolsApiClient.sourceVersioning().startDatabase(scrapedSource.getSourceMetaData().getChannel().getDatabaseName());
        if(response.getException() != null){
            info.setStatus(Status.FAILED);
            infoServiceFacade.updateFailure(info);
        } else {
            doLoad(info, scrapedSource, modelResolver.resolve(scrapedSource.getModelClassName()));
        }

    }
    @Override
    public void loadPage(Long pageId) {
        notNull(pageId, "Scraping page id can not be null");
        final ScrapedUnitInfoResponse scrapedPage = infoServiceFacade.getPage(pageId);
        final SourceInfoResponse scrapedSource = sourceService.findByInfoId(scrapedPage.getScrapedSourceInfoId());
        doLoadPage(scrapedPage, scrapedSource, modelResolver.resolve(scrapedSource.getModelClassName()));
    }

    private void doLoadPage(ScrapedUnitInfoResponse scrapedPage, SourceInfoResponse scrapedSource, AbstractSource abstractSource) {
        final ScrapActionDetailsResponse info = infoServiceFacade.create(scrapedSource.getId(), scrapedSource.getDocumentType(), scrapedSource.getSourceUrl());
        final Scraper scraper = abstractSource.getScraper();
        final Parser parser = abstractSource.getParser();
        final Subscriber<GenericScrapedUnit> subscriber = subscribeOnScrapingEvent(info, parser);
        scraper.scrapPage(new GenericScrapingSource<>(scrapedPage.getUrl()), subscriber);
    }

    private void doLoad(final ScrapActionDetailsResponse info, final SourceInfoResponse scrapedSource, final AbstractSource abstractSource) {

        final Scraper scraper = abstractSource.getScraper();
        final Parser parser = abstractSource.getParser();
        final Subscriber<GenericScrapedUnit> subscriber = subscribeOnScrapingEvent(info, parser);
        scraper.scrap(new GenericScrapingSource<>(scrapedSource.getSourceUrl()), subscriber);
    }

    private Subscriber<GenericScrapedUnit> subscribeOnScrapingEvent(final ScrapActionDetailsResponse info, final Parser parser) {
        return new Subscriber<GenericScrapedUnit>() {
            @Override
            public void onCompleted() {
                infoServiceFacade.updateSuccess(info);
                entityToolsApiClient.sourceVersioning().completeDatabase(info.getSourceMetadata().getChannel().getDatabaseName());
            }

            @Override
            public void onError(final Throwable throwable) {
                infoServiceFacade.updateFailure(info);
            }

            @Override
            public void onNext(GenericScrapedUnit scrapedUnit) {
                handleScrapedData(scrapedUnit, info, parser);
            }
        };
    }

    private void handleScrapedData(final GenericScrapedUnit scrapedUnit, final ScrapActionDetailsResponse info, final Parser parser) {
        if (scrapedUnit != null) {
            final ApiException scrapException = scrapedUnit.getApiException();
            if (scrapException == null) {
                delegateToParseAndPersist(info, parser, scrapedUnit);
            } else {
                infoServiceFacade.createFailure(info, scrapedUnit, LoadingStage.SCRAPING);
            }
        }
    }

    private void delegateToParseAndPersist(final ScrapActionDetailsResponse info, final Parser parser, final GenericScrapedUnit scrapedUnit) {
        try {
            final AbstractModel model = parser.parse(new GenericParsingSource(scrapedUnit.getDocument()));
            delegateToPersist(scrapedUnit, info, model);
        } catch (Exception e) {
            infoServiceFacade.createFailure(info, scrapedUnit, LoadingStage.PARSING);
        }
    }

    private void delegateToPersist(final GenericScrapedUnit scrapedUnit, final ScrapActionDetailsResponse info, final AbstractModel model) {
        try {
            final SourceMetaDataModel metaData = info.getSourceMetadata();
            model.setCountry(metaData.getCountry());
            model.setChannel(metaData.getChannel());
            model.setVersion(info.getVersion());
            final GenericResponseDTO<LoaderDTO> response = entityToolsApiClient.sourceVersioning().load(model);
            if (response.getException() == null && response.getResponse() != null) {
                infoServiceFacade.createSuccess(info, scrapedUnit, response.getResponse().getSourceModelMongoId());
            } else {
                infoServiceFacade.createFailure(info, scrapedUnit, response.getException().getStage());
            }
        } catch (Exception e) {
            if(e instanceof ApiException){
                final ApiException apiException = (ApiException) e;
                infoServiceFacade.createFailure(info, scrapedUnit, apiException.getStage());
            } else {
                infoServiceFacade.createFailure(info, scrapedUnit, LoadingStage.SOURCE_VERSIONING);
            }
        }
    }
}
