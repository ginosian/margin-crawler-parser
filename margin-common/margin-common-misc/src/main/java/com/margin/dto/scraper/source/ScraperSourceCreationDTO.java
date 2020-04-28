package com.margin.dto.scraper.source;

import com.margin.dto.scraper.SourceMetaDataDTO;
import com.margin.enums.CrawllingType;
import com.margin.enums.DocumentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScraperSourceCreationDTO {
    private SourceMetaDataDTO sourceMetaData;
    private String sourceName;
    private String sourceUrl;
    private DocumentType documentType;
    private LocalDateTime sourceFoundDate;
    private CrawllingType sourceType;
    private String description;
}
