package com.margin.service.model.scrap_action_details;


import com.margin.enums.DocumentType;
import com.margin.enums.Status;
import com.margin.service.model.SourceMetaDataModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScrapActionDetailsResponse {
    private SourceMetaDataModel sourceMetadata;
    private Long id;
    private Integer version;
    private DocumentType documentType;
    private Status status;
    private String dataPath;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
