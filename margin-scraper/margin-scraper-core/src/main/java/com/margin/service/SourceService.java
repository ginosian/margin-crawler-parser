package com.margin.service;

import com.margin.service.model.source_info.SourceExtendedInfoResponce;
import com.margin.service.model.source_info.SourceInfoCreationRequest;
import com.margin.service.model.source_info.SourceInfoResponse;
import com.margin.service.model.source_info.SourceInfoUpdateRequest;
import com.margin.service.model.SourceMetaDataModel;

import java.util.List;
import java.util.Optional;

public interface SourceService {

    SourceMetaDataModel createSourceMetaData(SourceMetaDataModel request);
    SourceMetaDataModel getSourceMetaData(Long id);
    Optional<SourceMetaDataModel> findSourceMetaData(SourceMetaDataModel request);
    List<SourceMetaDataModel> findAllSourceMetaData();
    List<SourceMetaDataModel> searchSourceMetaData(SourceMetaDataModel request);

    SourceInfoResponse create(SourceInfoCreationRequest request);
    SourceInfoResponse update(SourceInfoUpdateRequest request);
    SourceInfoResponse get(Long id);
    SourceInfoResponse findByInfoId(Long id);
    List<SourceInfoResponse> findAll();
    List<SourceInfoResponse> search(SourceMetaDataModel request);

    List<SourceExtendedInfoResponce> findAllWithDetails();
}
