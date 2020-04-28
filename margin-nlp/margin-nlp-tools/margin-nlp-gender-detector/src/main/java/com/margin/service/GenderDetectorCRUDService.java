package com.margin.service;

import com.margin.model.NLPResponse;
import com.margin.model.genderdetector.GenderDetectorCreateRequest;
import com.margin.model.genderdetector.GenderDetectorCreateResponse;

public interface GenderDetectorCRUDService {

    void save();
    GenderDetectorCreateResponse create(GenderDetectorCreateRequest request);
//    NameGenderResponse update(NameGenderEntityUpdateRequest request);
//    NameGenderResponse get(Long id);
//    List<NameGenderResponse> search(NameGenderEntitySearchRequest request);
}
