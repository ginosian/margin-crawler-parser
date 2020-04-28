package com.margin.service.impl;

import com.margin.entity.ScrapActionDetailsEntity;
import com.margin.entity.SourceInfoEntity;
import com.margin.entity.SourceMetaData;
import com.margin.enums.*;
import com.margin.mapper.BeanMapper;
import com.margin.repository.ScrapActionDetailsEntityRepository;
import com.margin.repository.SourceInfoEntityRepository;
import com.margin.repository.SourceMetaDataRepository;
import com.margin.service.SourceService;
import com.margin.service.model.SourceMetaDataModel;
import com.margin.service.model.source_info.SourceExtendedInfoResponce;
import com.margin.service.model.source_info.SourceInfoCreationRequest;
import com.margin.service.model.source_info.SourceInfoResponse;
import com.margin.service.model.source_info.SourceInfoUpdateRequest;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

@Service
public class SourceServiceImpl implements SourceService {
    private static final Logger logger = LoggerFactory.getLogger(SourceServiceImpl.class);

    @Autowired
    private SourceMetaDataRepository sourceMetaDataRepository;

    @Autowired
    private SourceInfoEntityRepository sourceInfoEntityRepository;

    @Autowired
    private ScrapActionDetailsEntityRepository scrapActionDetailsEntityRepository;

    @Autowired
    private BeanMapper mapper;

    @Transactional
    @Override
    public SourceMetaDataModel createSourceMetaData(final SourceMetaDataModel request) {
        notNull(request, "Request can not be null.");
        final Country country = request.getCountry();
        notNull(country, "Country can not be null.");
        final Channel channel = request.getChannel();
        notNull(channel, "Channel can not be null.");

        SourceMetaData entity = new SourceMetaData();
        entity.setCountry(country);
        entity.setChannel(channel);
        entity = sourceMetaDataRepository.save(entity);

        return mapper.map(entity, SourceMetaDataModel.class);
    }

    @Override
    public SourceMetaDataModel getSourceMetaData(final Long id) {
        notNull(id, "Id can not be null.");
        final SourceMetaData entity = sourceMetaDataRepository.getOne(id);
        return mapper.map(entity, SourceMetaDataModel.class);
    }

    @Override
    public Optional<SourceMetaDataModel> findSourceMetaData(final SourceMetaDataModel request) {
        notNull(request, "Request can not be null.");
        final Long id = request.getId();
        final Country country = request.getCountry();
        final Channel channel = request.getChannel();
        SourceMetaData metaData = null;
        if (id == null && country == null && channel == null) {
            throw new IllegalArgumentException("In order to find a source metadata at lease id or county and channel should be provided.");
        }
        if (id != null) {
            metaData = sourceMetaDataRepository.findById(id).orElse(null);
        }
        if (metaData == null) {
            if (country == null && channel == null) {
                throw new IllegalArgumentException("In order to find a source metadata county and channel should be provided.");
            }
            metaData = sourceMetaDataRepository.findFirstByCountryAndChannel(country, channel).orElse(null);
        }
        if (metaData == null) {
            return Optional.empty();
        } else {
            return Optional.of(mapper.map(metaData, SourceMetaDataModel.class));
        }
    }

    @Override
    public List<SourceMetaDataModel> findAllSourceMetaData() {
        final List<SourceMetaData> allEntities = sourceMetaDataRepository.findAll();
        return mapper.mapAsList(allEntities, SourceMetaDataModel.class);
    }

    @Override
    public List<SourceMetaDataModel> searchSourceMetaData(final SourceMetaDataModel request) {
        notNull(request, "Request can not be null.");
        final Long id = request.getId();
        final Country country = request.getCountry();
        final Channel channel = request.getChannel();
        SourceMetaData metaData = null;
        if (id == null && country == null && channel == null) {
            throw new IllegalArgumentException("In order to search for source metadata at lease one of id, county or channel should be provided.");
        }
        if (id != null) {
            metaData = sourceMetaDataRepository.findById(id).orElse(null);
        }
        if (metaData == null) {
            return mapper.mapAsList(sourceMetaDataRepository.findFirstByCountryOrChannel(country, channel), SourceMetaDataModel.class);
        } else {
            return Lists.newArrayList(mapper.map(metaData, SourceMetaDataModel.class));
        }
    }

    @Transactional
    @Override
    public SourceInfoResponse get(final Long id) {
        notNull(id, "Id can not be null.");
        return mapper.map(sourceInfoEntityRepository.getOne(id), SourceInfoResponse.class);
    }

    @Transactional
    @Override
    public SourceInfoResponse findByInfoId(final Long infoId) {
        notNull(infoId, "Scraped Info id can not be null.");
        final ScrapActionDetailsEntity infoResponse = scrapActionDetailsEntityRepository.getOne(infoId);
        final SourceInfoEntity entity = infoResponse.getSourceInfoEntity();

        return mapper.map(entity, SourceInfoResponse.class);
    }

    @Transactional
    @Override
    public SourceInfoResponse create(final SourceInfoCreationRequest request) {
        notNull(request, "Request can not be null.");

        final SourceMetaDataModel sourceMetaDataModel = request.getSourceMetaData();
        notNull(sourceMetaDataModel, "SourceMetaDataModel can not be null.");
        SourceMetaDataModel sourceMetaDataFromDb = findSourceMetaData(sourceMetaDataModel).orElse(null);
        if (sourceMetaDataFromDb == null) {
            sourceMetaDataFromDb = createSourceMetaData(sourceMetaDataModel);
        }

        final String sourceName = request.getSourceName();
        hasText(sourceName, "SourceName can not be null or empty.");

        final String sourceUrl = request.getSourceUrl();
        final DocumentType documentType = request.getDocumentType();
        final CrawllingType sourceType = request.getSourceType();
        final String modelCLassName = request.getModelClassName();

        SourceInfoEntity scrapDataEntity = new SourceInfoEntity(
                mapper.map(sourceMetaDataFromDb, SourceMetaData.class),
                sourceName,
                sourceUrl,
                documentType,
                request.getSourceFoundDate(),
                sourceType,
                modelCLassName,
                request.getDescription(),
                sourceIsLoadable(sourceUrl, documentType, sourceType, modelCLassName)
        );
        scrapDataEntity = sourceInfoEntityRepository.save(scrapDataEntity);
        return mapper.map(scrapDataEntity, SourceInfoResponse.class);
    }

    @Transactional
    @Override
    public SourceInfoResponse update(SourceInfoUpdateRequest request) {
        notNull(request, "Request can not be null.");
        final Long id = request.getId();
        final SourceInfoEntity sourceInfoEntity = sourceInfoEntityRepository.getOne(id);

        final String sourceUrl = request.getSourceUrl();
        final DocumentType documentType = request.getDocumentType();
        final CrawllingType sourceType = request.getSourceType();
        final String modelCLassName = request.getModelClassName();

        sourceInfoEntity.setSourceName(request.getSourceName());
        sourceInfoEntity.setSourceUrl(sourceUrl);
        sourceInfoEntity.setDocumentType(documentType);
        sourceInfoEntity.setSourceFoundDate(request.getSourceFoundDate());
        sourceInfoEntity.setSourceType(sourceType);
        sourceInfoEntity.setModelClassName(modelCLassName);
        sourceInfoEntity.setDescription(request.getDescription());
        sourceInfoEntity.setIsLoadable(sourceIsLoadable(sourceUrl, documentType, sourceType, modelCLassName));

        return mapper.map(sourceInfoEntityRepository.save(sourceInfoEntity), SourceInfoResponse.class);
    }

    @Override
    public List<SourceInfoResponse> findAll() {
        return mapper.mapAsList(sourceInfoEntityRepository.findAll(), SourceInfoResponse.class);
    }

    @Override
    public List<SourceInfoResponse> search(final SourceMetaDataModel request) {
        notNull(request, "Request can not be null.");
        final List<SourceMetaDataModel> sourceMetaDataModel = searchSourceMetaData(request);
        final List<SourceInfoResponse> sourceInfoRespons = new ArrayList<>();
        sourceMetaDataModel.forEach(sourceMetaDataModel1 -> {
            final List<SourceInfoEntity> scrapSourceEntities = sourceInfoEntityRepository.findBySourceMetaData(mapper.map(sourceMetaDataModel1, SourceMetaData.class));
            sourceInfoRespons.addAll(mapper.mapAsList(scrapSourceEntities, SourceInfoResponse.class));
        });
        return sourceInfoRespons;
    }

    private boolean sourceIsLoadable(final String sourceUrl,
                                     final DocumentType documentType,
                                     final CrawllingType sourceType,
                                     final String modelCLassName) {
        return StringUtils.isNoneEmpty(sourceUrl) && documentType != null && sourceType != null && StringUtils.isNoneEmpty(modelCLassName);
    }

    @Override
    public List<SourceExtendedInfoResponce> findAllWithDetails() {
        final List<SourceExtendedInfoResponce> responses = new ArrayList<>();
        final List<SourceInfoEntity> sourceInfoEntities = sourceInfoEntityRepository.findAll();
        sourceInfoEntities.forEach(sourceInfoEntity -> {
            final SourceMetaDataModel sourceMetaDataModel = mapper.map(sourceInfoEntity.getSourceMetaData(), SourceMetaDataModel.class);
            final SourceExtendedInfoResponce response = new SourceExtendedInfoResponce();
            response.setId(sourceInfoEntity.getId());
            response.setSourceMetaData(sourceMetaDataModel);
            response.setSourceName(sourceInfoEntity.getSourceName());
            response.setSourceUrl(sourceInfoEntity.getSourceUrl());
            response.setDocumentType(sourceInfoEntity.getDocumentType());
            response.setSourceFoundDate(sourceInfoEntity.getSourceFoundDate());
            response.setSourceType(sourceInfoEntity.getSourceType());
            response.setModelClassName(sourceInfoEntity.getModelClassName());
            response.setDescription(sourceInfoEntity.getDescription());
            response.setIsLoadable(sourceMetaDataModel.getChannel().isActive());
            final ScrapActionDetailsEntity lastSuccess = scrapActionDetailsEntityRepository.findFirstByStatusOrderByEndDateDesc(Status.SUCCESS);
            response.setLastSuccessLoadingDate(lastSuccess == null ? null : lastSuccess.getEndDate());
            final ScrapActionDetailsEntity lastFailed = scrapActionDetailsEntityRepository.findFirstByStatusOrderByStartDateDesc(Status.FAILED);
            response.setLastFailedLoadingDate(lastFailed == null ? null : lastFailed.getStartDate());
            response.setSuccessfulLoadingAmount(scrapActionDetailsEntityRepository.countByStatus(Status.SUCCESS));
            responses.add(response);
        });
        return responses;
    }
}
