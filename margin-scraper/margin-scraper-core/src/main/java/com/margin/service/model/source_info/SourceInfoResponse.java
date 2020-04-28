package com.margin.service.model.source_info;


import com.margin.enums.CrawllingType;
import com.margin.enums.DocumentType;
import com.margin.service.model.SourceMetaDataModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SourceInfoResponse {
    private Long id;
    private SourceMetaDataModel sourceMetaData;
    private String sourceName;
    private String sourceUrl;
    private DocumentType documentType;
    private LocalDateTime sourceFoundDate;
    private CrawllingType sourceType;
    private String modelClassName;
    private String description;
    private Boolean isLoadable;
}
