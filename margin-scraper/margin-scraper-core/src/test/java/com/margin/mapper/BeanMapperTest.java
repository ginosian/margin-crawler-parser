package com.margin.mapper;

import com.margin.dto.scraper.SourceMetaDataDTO;
import com.margin.dto.scraper.source.ScraperSourceCreationDTO;
import com.margin.entity.*;
import com.margin.service.model.SourceMetaDataModel;
import com.margin.service.model.failed_scraped_unit_info.FailedScrapedUnitInfoResponse;
import com.margin.service.model.scrap_action_details.ScrapActionDetailsResponse;
import com.margin.service.model.scraped_unit_info.ScrapedUnitInfoResponse;
import com.margin.service.model.source_info.SourceInfoCreationRequest;
import com.margin.service.model.source_info.SourceInfoResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class BeanMapperTest {

    @InjectMocks
    private BeanMapper beanMapper;

    @Test
    public void convertScrapSourceEntity() {
        final SourceInfoEntity target = random(SourceInfoEntity.class);
        final SourceInfoResponse response = beanMapper.map(target, SourceInfoResponse.class);
        assertEquals(response.getId(), target.getId());
        assertNotNull(response.getSourceMetaData());
        assertEquals(response.getSourceName(), target.getSourceName());
        assertEquals(response.getSourceUrl(), target.getSourceUrl());
        assertEquals(response.getSourceFoundDate(), target.getSourceFoundDate());
        assertEquals(response.getSourceType(), target.getSourceType());
        assertEquals(response.getDescription(), target.getDescription());
    }

    @Test
    public void convertScrapSourceDTO() {
        final ScraperSourceCreationDTO target = random(ScraperSourceCreationDTO.class);
        final SourceInfoCreationRequest response = beanMapper.map(target, SourceInfoCreationRequest.class);
        assertNotNull(response.getSourceMetaData());
        assertEquals(response.getSourceName(), target.getSourceName());
        assertEquals(response.getSourceUrl(), target.getSourceUrl());
        assertEquals(response.getDocumentType(), target.getDocumentType());
        assertEquals(response.getSourceFoundDate(), target.getSourceFoundDate());
        assertEquals(response.getSourceType(), target.getSourceType());
        assertEquals(response.getDescription(), target.getDescription());
    }
    @Test
    public void convertSourceMetaDataDTO() {
        final SourceMetaDataModel target = random(SourceMetaDataModel.class);
        final SourceMetaDataDTO response = beanMapper.map(target, SourceMetaDataDTO.class);
        assertEquals(response.getId(), target.getId());
        assertEquals(response.getChannel(), target.getChannel());
        assertEquals(response.getCountry(), target.getCountry());
    }

    @Test
    public void convertSourceMetaData(){
        final SourceMetaData sourceMetaData = random(SourceMetaData.class);
        final SourceMetaDataModel sourceMetaDataModel = beanMapper.map(sourceMetaData, SourceMetaDataModel.class);
        assertNotNull(sourceMetaDataModel.getId());
        assertNotNull(sourceMetaDataModel.getCountry());
        assertNotNull(sourceMetaDataModel.getChannel());

    }

    @Test
    public void convertScrapSourceInfo(){
        final ScrapActionDetailsEntity scrapActionDetailsEntity = random(ScrapActionDetailsEntity.class);
        final ScrapActionDetailsResponse scrapActionDetailsResponse = beanMapper.map(scrapActionDetailsEntity, ScrapActionDetailsResponse.class);
        assertNotNull(scrapActionDetailsResponse.getId());
        assertNotNull(scrapActionDetailsResponse.getVersion());
        assertNotNull(scrapActionDetailsResponse.getDocumentType());
        assertNotNull(scrapActionDetailsResponse.getStatus());
        assertNotNull(scrapActionDetailsResponse.getStartDate());
        assertNotNull(scrapActionDetailsResponse.getEndDate());

    }

    @Test
    public void convertScrapedPage(){
        final ScrapedUnitInfoEntity scrapedUnitInfoEntity = random(ScrapedUnitInfoEntity.class);
        final ScrapedUnitInfoResponse scrapedUnitInfoResponse = beanMapper.map(scrapedUnitInfoEntity, ScrapedUnitInfoResponse.class);
        assertNotNull(scrapedUnitInfoResponse.getId());
        assertNull(scrapedUnitInfoResponse.getScrapedSourceInfoId());
        assertNotNull(scrapedUnitInfoResponse.getSourceMetaData());
        assertNotNull(scrapedUnitInfoResponse.getUrl());
        assertNotNull(scrapedUnitInfoResponse.getFileName());
        assertNotNull(scrapedUnitInfoResponse.getScrapDate());
        assertNotNull(scrapedUnitInfoResponse.getStatus());
        assertNotNull(scrapedUnitInfoResponse.getIndex());
    }

    @Test
    public void convert(){
        final FailedScrapedUnitInfoEntity failedScrapedUnitInfoEntity = random(FailedScrapedUnitInfoEntity.class);
        final FailedScrapedUnitInfoResponse failedScrapedUnitInfoResponse = beanMapper.map(failedScrapedUnitInfoEntity, FailedScrapedUnitInfoResponse.class);
        assertNotNull(failedScrapedUnitInfoResponse.getId());
        assertNull(failedScrapedUnitInfoResponse.getSourceMetaData());
        assertNotNull(failedScrapedUnitInfoResponse.getStage());
        assertNull(failedScrapedUnitInfoResponse.getHttpError());
        assertNotNull(failedScrapedUnitInfoResponse.getErrorCode());
        assertNotNull(failedScrapedUnitInfoResponse.getErrorMessage());
    }
}