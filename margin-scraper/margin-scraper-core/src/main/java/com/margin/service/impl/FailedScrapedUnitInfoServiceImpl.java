package com.margin.service.impl;

import com.margin.entity.FailedScrapedUnitInfoEntity;
import com.margin.mapper.BeanMapper;
import com.margin.repository.FailedScrapedUnitInfoEntityRepository;
import com.margin.service.FailedScrapedUnitInfoService;
import com.margin.service.model.failed_scraped_unit_info.FailedScrapedUnitInfoRequest;
import com.margin.service.model.failed_scraped_unit_info.FailedScrapedUnitInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;
import static org.springframework.util.Assert.hasText;

@Service
public class FailedScrapedUnitInfoServiceImpl implements FailedScrapedUnitInfoService {
    private static final Logger logger = LoggerFactory.getLogger(FailedScrapedUnitInfoServiceImpl.class);

    @Autowired
    private BeanMapper mapper;

    @Autowired
    private FailedScrapedUnitInfoEntityRepository repository;

    @Transactional
    @Override
    public FailedScrapedUnitInfoResponse create(final FailedScrapedUnitInfoRequest request) {
        notNull(request, "Request cannot be null.");
        hasText(request.getErrorMessage(), "Error message can not be null.");
        FailedScrapedUnitInfoEntity entity = new FailedScrapedUnitInfoEntity();
        entity.setStage(request.getStage());
        entity.setErrorCode(request.getErrorCode());
        entity.setErrorMessage(request.getErrorMessage());
        entity = repository.save(entity);
        return mapper.map(entity, FailedScrapedUnitInfoResponse.class);
    }

    @Override
    public FailedScrapedUnitInfoResponse get(final Long id) {
        notNull(id, "The failed page id cannot be null.");
        final FailedScrapedUnitInfoEntity entity = repository.getOne(id);
        return mapper.map(entity, FailedScrapedUnitInfoResponse.class);
    }

    @Override
    public List<FailedScrapedUnitInfoResponse> getAll() {
        final List<FailedScrapedUnitInfoEntity> entities = repository.findAll();
        return mapper.mapAsList(entities, FailedScrapedUnitInfoResponse.class);
    }

}
