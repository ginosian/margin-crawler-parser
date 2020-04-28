package com.margin.dto.scraper.source;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScraperSourceExtendedDTO extends ScraperSourceDTO {
    private LocalDateTime lastSuccessLoadingDate;
    private LocalDateTime lastFailedLoadingDate;
    private Integer successfulLoadingAmount;
}
