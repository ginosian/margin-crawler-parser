package com.margin.service.model.scraped_unit_info;

import com.margin.enums.Status;
import com.margin.service.model.SourceMetaDataModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScrapedUnitInfoSearchRequest {
    private SourceMetaDataModel source;
    private Integer version;
    private Status status;
}
