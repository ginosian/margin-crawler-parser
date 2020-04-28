package com.margin.service.source.component;

import com.margin.entity.thirdparty.source.entity.APIThresholdInfoEntity;
import com.margin.entity.thirdparty.source.entity.ThirdPartySourceEntity;
import com.margin.model.source.APIThresholdInfoModel;
import com.margin.model.source.ThirdPartySourceCreationRequest;
import com.margin.model.source.ThirdPartySourceResponse;
import org.springframework.stereotype.Component;

@Component
public class ThirdPartySourceConverter {

    public ThirdPartySourceEntity convert(final ThirdPartySourceCreationRequest request) {
        final ThirdPartySourceEntity entity = new ThirdPartySourceEntity();
        entity.setName(request.getName());
        entity.setUrl(request.getUrl());
        entity.setDescription(request.getDescription());
        entity.setServiceTypes(request.getServiceTypes());
        entity.setCrawllingType(request.getCrawllingType());
        entity.setMetadata(request.getMetadata());
        entity.setLanguages(request.getLanguages());
        final APIThresholdInfoModel threshold = request.getThreshold();
        if(threshold != null){
            entity.setThreshold(convert(threshold));
        }
        return entity;
    }

    public APIThresholdInfoEntity convert(final APIThresholdInfoModel request) {
        final APIThresholdInfoEntity entity = new APIThresholdInfoEntity();
        return entity;
    }

    public ThirdPartySourceResponse convert(final ThirdPartySourceEntity entity) {
        final ThirdPartySourceResponse model = new ThirdPartySourceResponse();
        model.setId(entity.get_id());
        model.setUrl(entity.getUrl());
        model.setName(entity.getName());
        model.setServiceTypes(entity.getServiceTypes());
        model.setDescription(entity.getDescription());
        model.setMetadata(entity.getMetadata());
        model.setCrawllingType(entity.getCrawllingType());
        model.setLanguages(entity.getLanguages());
        final APIThresholdInfoEntity thresholdInfo = entity.getThreshold();
        if(thresholdInfo != null){
            model.setThreshold(convert(thresholdInfo));
        }
        return model;
    }

    public APIThresholdInfoModel convert(final APIThresholdInfoEntity entity) {
        final APIThresholdInfoModel model = new APIThresholdInfoModel();
        return model;
    }
}
