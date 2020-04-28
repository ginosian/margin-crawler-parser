package com.margin.scraper;

import com.margin.enums.FileType;
import com.margin.enums.Status;
import com.margin.error.ApiException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericScrapedUnit<Doc> {
    private Doc document;
    private FileType fileType;
    private String url;
    private String fileName;
    private LocalDateTime scrapDate;
    private Status status;
    private Long index;
    private String context;
    private ApiException apiException;
}
