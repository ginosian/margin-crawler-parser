package com.margin.service.model.source_info;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SourceExtendedInfoResponce extends SourceInfoResponse{
    private LocalDateTime lastSuccessLoadingDate;
    private LocalDateTime lastFailedLoadingDate;
    private Integer successfulLoadingAmount;
}
