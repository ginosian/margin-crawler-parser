package com.margin.service.model.scraped_unit_info;


import com.margin.enums.Status;
import com.margin.service.model.failed_scraped_unit_info.FailedScrapedUnitInfoRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScrapedUnitInfoCreationRequest {
    private Long scrapedSourceInfoId;
    private String mongoId;
    private FailedScrapedUnitInfoRequest failedPage;
    private String url;
    private String fileName;
    private LocalDateTime scrapDate;
    private Status status;
    private Long index;
    private String context;
}
