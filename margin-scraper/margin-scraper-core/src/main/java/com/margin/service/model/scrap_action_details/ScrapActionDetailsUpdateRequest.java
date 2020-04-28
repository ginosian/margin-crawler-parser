package com.margin.service.model.scrap_action_details;


import com.margin.enums.DocumentType;
import com.margin.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScrapActionDetailsUpdateRequest {
    private Long id;
    private LocalDateTime endDate;
    private DocumentType documentType;
    private Status status;
}
