package com.margin.service;

import com.margin.model.NLPDataResponse;
import com.margin.model.NLPRequest;

public interface GenderThirdPartyService {
    NLPDataResponse detectGender(NLPRequest request);
}
