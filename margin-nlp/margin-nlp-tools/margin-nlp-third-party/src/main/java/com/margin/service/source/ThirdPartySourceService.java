package com.margin.service.source;

import com.margin.model.source.ThirdPartySourceCreationRequest;
import com.margin.model.source.ThirdPartySourceResponse;
import com.margin.model.source.ThirdPartySourceUpdateRequest;

import java.util.List;

public interface ThirdPartySourceService {

    ThirdPartySourceResponse get(String id);
    List<ThirdPartySourceResponse> findAll();
    ThirdPartySourceResponse create(ThirdPartySourceCreationRequest request);
    ThirdPartySourceResponse update(ThirdPartySourceUpdateRequest request);
}
