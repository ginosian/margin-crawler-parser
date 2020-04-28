package com.margin.service.model.source_info;


import com.margin.enums.CrawllingType;
import com.margin.enums.DocumentType;
import com.margin.service.model.SourceMetaDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SourceInfoCreationRequest {
    private SourceMetaDataModel sourceMetaData;
    private String sourceName;
    private String sourceUrl;
    private DocumentType documentType;
    private LocalDateTime sourceFoundDate;
    private CrawllingType sourceType;
    private String modelClassName;
    private String description;
}
