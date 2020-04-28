package com.margin.dto.scraper.source;

import com.margin.enums.CrawllingType;
import com.margin.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScraperSourceUpdateDTO {
    private Long id;
    private String sourceName;
    private String sourceUrl;
    private DocumentType documentType;
    private LocalDateTime sourceFoundDate;
    private CrawllingType sourceType;
    private String modelClassName;
    private String description;
}
