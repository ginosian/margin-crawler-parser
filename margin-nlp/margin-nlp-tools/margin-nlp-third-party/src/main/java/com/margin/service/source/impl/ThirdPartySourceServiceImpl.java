package com.margin.service.source.impl;

import com.margin.error.ApiException;
import com.margin.enums.NLPServiceType;
import com.margin.enums.NLPThirdParty;
import com.margin.entity.thirdparty.source.entity.ThirdPartySourceEntity;
import com.margin.model.source.APIThresholdInfoModel;
import com.margin.model.source.ThirdPartySourceCreationRequest;
import com.margin.model.source.ThirdPartySourceResponse;
import com.margin.model.source.ThirdPartySourceUpdateRequest;
import com.margin.repository.ThirdPartySourceRepository;
import com.margin.service.source.ThirdPartySourceService;
import com.margin.service.source.component.ThirdPartySourceConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.Validate.notNull;
import static org.springframework.util.Assert.hasText;

@Service
public class ThirdPartySourceServiceImpl implements ThirdPartySourceService {

    @Autowired
    private ThirdPartySourceRepository thirdPartySourceRepository;

    @Autowired
    private ThirdPartySourceConverter converter;

    @Override
    public ThirdPartySourceResponse get(final String id) {
        notNull(id, "id can not be null");
        return converter.convert(getOne(id));
    }

    private ThirdPartySourceEntity getOne(final String id) {
        final ThirdPartySourceEntity entity = thirdPartySourceRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new ApiException(String.format("Could not find third party source with ID:'%s'", id));
        } else {
            return entity;
        }
    }

    @Override
    public List<ThirdPartySourceResponse> findAll() {
        return thirdPartySourceRepository.findAll().stream().map(entity -> converter.convert(entity)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ThirdPartySourceResponse create(final ThirdPartySourceCreationRequest request) {
        notNull(request, "request can not be null");
        final NLPThirdParty name = request.getName();
        final String url = request.getUrl();
        final Set<NLPServiceType> serviceTypes = request.getServiceTypes();
        notNull(name, "name can not be null");
        hasText(url, "url can not be null");
        if (CollectionUtils.isEmpty(serviceTypes)) {
            throw new ApiException("Third party service can not be created, provided services are not defined");
        }
        final ThirdPartySourceEntity entity = converter.convert(request);
        return converter.convert(thirdPartySourceRepository.save(entity));
    }

    @Transactional
    @Override
    public ThirdPartySourceResponse update(final ThirdPartySourceUpdateRequest request) {
        notNull(request, "request can not be null");
        final String id = request.getId();
        hasText(id, "id can not be null");
        final ThirdPartySourceEntity entity = getOne(id);
        final NLPThirdParty name = request.getName();
        if (name != null) {
            entity.setName(name);
        }
        final String url = request.getUrl();
        if (StringUtils.isNoneEmpty(url)) {
            entity.setUrl(url);
        }
        final Set<NLPServiceType> serviceTypes = request.getServiceTypes();
        if (!CollectionUtils.isEmpty(serviceTypes)) {
            entity.setServiceTypes(serviceTypes);
        }
        entity.setDescription(request.getDescription());
        entity.setCrawllingType(request.getCrawllingType());
        final APIThresholdInfoModel threshold = request.getThreshold();
        if (threshold != null) {
            entity.setThreshold(converter.convert(request.getThreshold()));
        }
        entity.setLanguages(request.getLanguages());
        entity.setMetadata(request.getMetadata());
        return converter.convert(thirdPartySourceRepository.save(entity));
    }
}
