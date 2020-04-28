package com.margin.loading.facade;

import com.margin.enums.DocumentType;
import com.margin.enums.LoadingStage;
import com.margin.enums.Status;
import com.margin.error.ApiException;
import com.margin.mapper.BeanMapper;
import com.margin.scraper.GenericScrapedUnit;
import com.margin.service.ScrapingInfoService;
import com.margin.service.model.failed_scraped_unit_info.FailedScrapedUnitInfoRequest;
import com.margin.service.model.scrap_action_details.ScrapActionDetailsCreationRequest;
import com.margin.service.model.scrap_action_details.ScrapActionDetailsResponse;
import com.margin.service.model.scrap_action_details.ScrapActionDetailsUpdateRequest;
import com.margin.service.model.scraped_unit_info.ScrapedUnitInfoCreationRequest;
import com.margin.service.model.scraped_unit_info.ScrapedUnitInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class SourceInfoServiceFacade {
    private static final Logger logger = LoggerFactory.getLogger(SourceInfoServiceFacade.class);

    @Autowired
    private ScrapingInfoService scrapingInfoService;

    @Autowired
    private BeanMapper mapper;

    @Transactional
    public ScrapedUnitInfoResponse getPage(final Long id) {
        return scrapingInfoService.getPage(id);
    }

    @Transactional
    public ScrapActionDetailsResponse create(final Long scrapedSourceId, final DocumentType documentType, final String url) {
        final ScrapActionDetailsCreationRequest creationRequest = new ScrapActionDetailsCreationRequest();
        creationRequest.setScrapSourceId(scrapedSourceId);
        creationRequest.setDocumentType(documentType);
        creationRequest.setDataPath(url);
        return scrapingInfoService.create(creationRequest);
    }

    @Transactional
    public void updateSuccess(final ScrapActionDetailsResponse info){
        info.setStatus(Status.SUCCESS);
        info.setEndDate(LocalDateTime.now());
        scrapingInfoService.update(mapper.map(info, ScrapActionDetailsUpdateRequest.class));
    }

    @Transactional
    public void updateFailure(final ScrapActionDetailsResponse info){
        info.setStatus(Status.FAILED);
        info.setEndDate(LocalDateTime.now());
        scrapingInfoService.update(mapper.map(info, ScrapActionDetailsUpdateRequest.class));
    }

    @Transactional
    public void createSuccess(final ScrapActionDetailsResponse info, final GenericScrapedUnit scrapedUnit, final String mongoId){
        final ScrapedUnitInfoCreationRequest request = create(info, scrapedUnit);
        request.setMongoId(mongoId);
        request.setStatus(Status.SUCCESS);
        scrapingInfoService.create(request);
    }

    @Transactional
    public void createFailure(final ScrapActionDetailsResponse info, final GenericScrapedUnit scrapedUnit, final LoadingStage stage){
        final ScrapedUnitInfoCreationRequest request = create(info, scrapedUnit);
        request.setStatus(Status.FAILED);
        final ApiException exception = scrapedUnit.getApiException();
        request.setFailedPage(create(exception, stage));
        scrapingInfoService.create(request);
    }

    private FailedScrapedUnitInfoRequest create(final ApiException exception, final LoadingStage stage){
        final FailedScrapedUnitInfoRequest request = new FailedScrapedUnitInfoRequest();
        if (exception == null){
            request.setStage(stage);
            request.setErrorMessage("An unknown exception occurred during loading.");
        } else {
            request.setStage(stage);
            request.setErrorCode(exception.getResponseStatusCode());
            request.setErrorMessage(exception.getMessage());
        }
        return request;
    }

    private ScrapedUnitInfoCreationRequest create(final ScrapActionDetailsResponse info, final GenericScrapedUnit scrapedUnit){
        final ScrapedUnitInfoCreationRequest request = new ScrapedUnitInfoCreationRequest();
        request.setScrapedSourceInfoId(info.getId());
        request.setUrl(scrapedUnit.getUrl());
        request.setFileName(scrapedUnit.getFileName());
        request.setScrapDate(LocalDateTime.now());
        request.setIndex(scrapedUnit.getIndex());
        request.setContext(scrapedUnit.getContext());
        request.setFailedPage(null);
        return request;
    }
}
