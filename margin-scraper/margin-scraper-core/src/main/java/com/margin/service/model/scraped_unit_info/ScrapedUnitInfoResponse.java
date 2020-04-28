package com.margin.service.model.scraped_unit_info;


import com.margin.enums.Status;
import com.margin.entity.IndexInfo;
import com.margin.service.model.SourceMetaDataModel;
import com.margin.service.model.failed_scraped_unit_info.FailedScrapedUnitInfoResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScrapedUnitInfoResponse {
    private Long id;
    private Long scrapedSourceInfoId;
    private SourceMetaDataModel sourceMetaData;
    private String url;
    private String fileName;
    private LocalDateTime scrapDate;
    private Status status;
    private IndexInfo index;
    private FailedScrapedUnitInfoResponse failedScrapedUnitInfoResponse;
}
