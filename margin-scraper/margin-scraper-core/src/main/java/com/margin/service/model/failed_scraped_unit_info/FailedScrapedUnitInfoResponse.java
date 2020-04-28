package com.margin.service.model.failed_scraped_unit_info;

import com.margin.enums.LoadingStage;
import com.margin.service.model.SourceMetaDataModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FailedScrapedUnitInfoResponse {
    private Long id;
    private SourceMetaDataModel sourceMetaData;
    private LoadingStage stage;
    private String httpError;
    private String errorCode;
    private String errorMessage;
}
