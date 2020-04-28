package com.margin.service.model.failed_scraped_unit_info;

import com.margin.enums.LoadingStage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FailedScrapedUnitInfoRequest {
    private LoadingStage stage;
    private Integer errorCode;
    private String errorMessage;
}
