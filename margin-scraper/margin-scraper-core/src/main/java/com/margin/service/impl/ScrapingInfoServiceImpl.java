package com.margin.service.impl;

import com.margin.enums.DocumentType;
import com.margin.enums.Status;
import com.margin.entity.*;
import com.margin.mapper.BeanMapper;
import com.margin.repository.FailedScrapedUnitInfoEntityRepository;
import com.margin.repository.ScrapedUnitInfoEntityRepository;
import com.margin.repository.ScrapActionDetailsEntityRepository;
import com.margin.repository.SourceInfoEntityRepository;
import com.margin.service.ScrapingInfoService;
import com.margin.service.SourceService;
import com.margin.service.model.*;
import com.margin.service.model.scrap_action_details.ScrapActionDetailsCreationRequest;
import com.margin.service.model.scrap_action_details.ScrapActionDetailsResponse;
import com.margin.service.model.scrap_action_details.ScrapActionDetailsSearchRequest;
import com.margin.service.model.scrap_action_details.ScrapActionDetailsUpdateRequest;
import com.margin.service.model.scraped_unit_info.ScrapedUnitInfoCreationRequest;
import com.margin.service.model.scraped_unit_info.ScrapedUnitInfoResponse;
import com.margin.service.model.scraped_unit_info.ScrapedUnitInfoSearchRequest;
import com.margin.service.model.scraped_unit_info.ScrapedUnitInfoUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
public class ScrapingInfoServiceImpl implements ScrapingInfoService {
    private static final Logger logger = LoggerFactory.getLogger(ScrapingInfoServiceImpl.class);

    @Autowired
    private BeanMapper mapper;

    @Autowired
    private ScrapActionDetailsEntityRepository scrapActionDetailsEntityRepository;

    @Autowired
    private ScrapedUnitInfoEntityRepository scrapedUnitInfoEntityRepository;

    @Autowired
    private SourceInfoEntityRepository sourceInfoEntityRepository;

    @Autowired
    private FailedScrapedUnitInfoEntityRepository failedScrapedUnitInfoEntityRepository;

    @Autowired
    private SourceService sourceService;

    @Transactional
    @Override
    public ScrapActionDetailsResponse create(final ScrapActionDetailsCreationRequest request) {
        notNull(request, "Request cannot be null.");
        final Long scrapSourceId = request.getScrapSourceId();
        notNull(scrapSourceId, "Scrap source id can not be null.");
        final DocumentType documentType = request.getDocumentType();
        notNull(documentType, "Document type can not be null.");
        final String dataPath = request.getDataPath();
        notNull(dataPath, "Datapath type can not be null.");
        final SourceInfoEntity scrapSource = sourceInfoEntityRepository.getOne(scrapSourceId);
        notNull(scrapSource, "In order to create ScrapActionDetailsEntity, valid id of ScrapSource should be provided.");

        logger.trace("Storing ScrapActionDetails...");
        final ScrapActionDetailsEntity lastVersion = scrapActionDetailsEntityRepository.findFirstBySourceInfoEntity_IdOrderByVersionDesc(scrapSourceId);
        ScrapActionDetailsEntity entity = new ScrapActionDetailsEntity();
        entity.setSourceMetadata(scrapSource.getSourceMetaData());
        if(lastVersion != null){
            entity.setVersion(lastVersion.getVersion() + 1);
        } else {
            entity.setVersion(1);
        }
        entity.setSourceInfoEntity(scrapSource);
        entity.setStartDate(LocalDateTime.now());
        entity.setDataPath(dataPath);
        entity.setStatus(Status.ON_START);
        entity.setDocumentType(documentType);
        entity = scrapActionDetailsEntityRepository.save(entity);
        logger.debug("Done storing ScrapActionDetails with id: '{}'.", entity.getId());

        return mapper.map(entity, ScrapActionDetailsResponse.class);
    }

    @Override
    public ScrapedUnitInfoResponse getPage(final Long id) {
        notNull(id, "Id can not be null.");
        logger.trace("Getting page with id: '{}'...", id);
        final ScrapedUnitInfoEntity entity = scrapedUnitInfoEntityRepository.getOne(id);
        final ScrapedUnitInfoResponse scrapedUnitInfoResponse = mapper.map(entity, ScrapedUnitInfoResponse.class);
        scrapedUnitInfoResponse.setScrapedSourceInfoId(entity.getScrapActionDetailsEntity().getId());
        logger.debug("Done getting page with id: '{}'.", entity.getId());

        return scrapedUnitInfoResponse;
    }

    @Override
    public ScrapActionDetailsResponse get(final Long id) {
        notNull(id, "Id can not be null.");
        logger.trace("Getting ScrapActionDetails with id: '{}'...", id);
        final ScrapActionDetailsEntity entity = scrapActionDetailsEntityRepository.getOne(id);
        logger.debug("Done getting ScrapActionDetails with id: '{}'.", entity.getId());

        return mapper.map(entity, ScrapActionDetailsResponse.class);
    }

    @Transactional
    @Override
    public ScrapActionDetailsResponse update(final ScrapActionDetailsUpdateRequest request) {
        notNull(request, "Request cannot be null.");
        notNull(request.getId(), "Id can not be null.");
        logger.trace("Updating ScrapActionDetails with id: '{}'...", request.getId());
        ScrapActionDetailsEntity entity = scrapActionDetailsEntityRepository.getOne(request.getId());
        if (request.getDocumentType() != null) {
            entity.setDocumentType(request.getDocumentType());
        }
        if (request.getEndDate() != null) {
            entity.setEndDate(request.getEndDate());
        }
        if(request.getStatus() != null){
            entity.setStatus(request.getStatus());
        }
        entity = scrapActionDetailsEntityRepository.save(entity);
        logger.debug("Done updating ScrapActionDetails with id: '{}'.", entity.getId());

        return mapper.map(entity, ScrapActionDetailsResponse.class);
    }


    @Override
    public List<ScrapActionDetailsResponse> search(final ScrapActionDetailsSearchRequest request) {
        notNull(request, "Request cannot be null.");
        SourceMetaDataModel sourceMetaDataModel = request.getSourceMetaData();
        notNull(sourceMetaDataModel, "Source can not be null.");
        sourceMetaDataModel = sourceService.findSourceMetaData(sourceMetaDataModel).orElse(null);
        notNull(sourceMetaDataModel, "");
        logger.trace("Searching ScrapActionDetails by country: '{}', channel: '{}', version: '{}' or status: '{}'...",
                sourceMetaDataModel.getCountry(), sourceMetaDataModel.getChannel(),
                request.getVersion(), request.getStatus());
        final List<ScrapActionDetailsEntity> entityList =
                scrapActionDetailsEntityRepository.findAllBySourceMetadataAndVersionOrStatus(
                        mapper.map(sourceMetaDataModel, SourceMetaData.class),
                        request.getVersion(),
                        request.getStatus()
        );
        logger.debug("Done searching {} ScrapActionDetails items.", entityList.size());

        return mapper.mapAsList(entityList, ScrapActionDetailsResponse.class);
    }

    @Transactional
    @Override
    public ScrapedUnitInfoResponse create(final ScrapedUnitInfoCreationRequest request) {
        notNull(request, "Request can not be null.");
        notNull(request.getStatus(), "Status can not be null.");
        notNull(request.getUrl(), "Url can not be null.");
        notNull(request.getScrapedSourceInfoId(), "Scraped source info id can not be null.");

        logger.trace("Storing ScrapedUnitInfo...");
        final ScrapActionDetailsEntity scrapActionDetailsEntity = scrapActionDetailsEntityRepository.getOne(request.getScrapedSourceInfoId());
        ScrapedUnitInfoEntity entity = new ScrapedUnitInfoEntity();
        entity.setSourceMetaData(scrapActionDetailsEntity.getSourceMetadata());
        entity.setScrapActionDetailsEntity(scrapActionDetailsEntity);
        entity.setUrl(request.getUrl());
        entity.setFileName(request.getFileName());
        entity.setScrapDate(LocalDateTime.now());
        entity.setStatus(request.getStatus());
        final IndexInfo indexInfo = new IndexInfo();
        mapper.map(request, indexInfo);
        entity.setIndex(indexInfo);
        entity.setMongoId(request.getMongoId());

        if (request.getFailedPage() != null) {
            FailedScrapedUnitInfoEntity failedScrapedUnitInfoEntity = new FailedScrapedUnitInfoEntity();
            mapper.map(request.getFailedPage(), failedScrapedUnitInfoEntity);
            failedScrapedUnitInfoEntity = failedScrapedUnitInfoEntityRepository.save(failedScrapedUnitInfoEntity);
            entity.setFailedScrapedUnitInfoEntity(failedScrapedUnitInfoEntity);
        }
        entity = scrapedUnitInfoEntityRepository.save(entity);
        logger.debug("Done storing ScrapedUnitInfo with id: '{}'.", entity.getId());

        return mapper.map(entity, ScrapedUnitInfoResponse.class);
    }

    @Override
    public ScrapedUnitInfoResponse update(ScrapedUnitInfoUpdateRequest request) {
        notNull(request, "Request can not be null.");
        notNull(request.getId(), "Id can not be null.");

        logger.trace("Updating ScrapedUnitInfo with id: '{}'...", request.getId());
        ScrapedUnitInfoEntity entity = scrapedUnitInfoEntityRepository.getOne(request.getId());
        if (request.getContext() != null) {
            if (entity.getIndex() == null) {
                entity.setIndex(new IndexInfo());
            }
            entity.getIndex().setContext(request.getContext());
        }
        if (request.getIndex() != null) {
            if (entity.getIndex() == null) {
                entity.setIndex(new IndexInfo());
            }
            entity.getIndex().setIndex(request.getIndex());
        }
        if (request.getFailedPage() != null) {
            final FailedScrapedUnitInfoEntity failedScrapedUnitInfoEntity = new FailedScrapedUnitInfoEntity();
            mapper.map(request.getFailedPage(), failedScrapedUnitInfoEntity);
            entity.setFailedScrapedUnitInfoEntity(failedScrapedUnitInfoEntity);
        }
        if (request.getFileName() != null) {
            entity.setFileName(request.getFileName());
        }
        if (request.getScrapDate() != null) {
            entity.setScrapDate(request.getScrapDate());
        }
        if (request.getScrapedSourceInfoId() != null) {
            entity.setScrapActionDetailsEntity(scrapActionDetailsEntityRepository.getOne(request.getScrapedSourceInfoId()));
        }
        if (request.getMongoId() != null) {
            entity.setMongoId(request.getMongoId());
        }
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
        if (request.getUrl() != null) {
            entity.setUrl(request.getUrl());
        }
        entity = scrapedUnitInfoEntityRepository.save(entity);
        logger.debug("Done updating ScrapedUnitInfo with id: '{}'.", entity.getId());
        final ScrapedUnitInfoResponse response = new ScrapedUnitInfoResponse();
        mapper.map(entity, response);

        return response;
    }

    @Override
    public List<ScrapedUnitInfoResponse> search(final ScrapedUnitInfoSearchRequest request) {
        notNull(request, "Request cannot be null.");
        notNull(request.getSource(), "Source can not be null.");
        notNull(request.getStatus(), "Status can not be null.");
        notNull(request.getVersion(), "Version can not be null.");

        logger.trace("Searching ScrapedUnitInfo by country: '{}', channel: '{}', version: '{}' or status: '{}'...",
                request.getSource().getCountry(), request.getSource().getChannel(),
                request.getVersion(), request.getStatus());
        final List<ScrapedUnitInfoEntity> entityList =
                scrapedUnitInfoEntityRepository.findBySourceMetaData_IdAndScrapActionDetailsEntity_VersionAndStatus(
                        request.getSource().getId(),
                        request.getVersion(),
                        request.getStatus());
        logger.debug("Done searching {} ScrapedUnitInfo items.", entityList.size());

        return mapper.mapAsList(entityList, ScrapedUnitInfoResponse.class);
    }
}
