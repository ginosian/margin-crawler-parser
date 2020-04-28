package com.margin.service.model.scrap_action_details;


import com.margin.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScrapActionDetailsCreationRequest {
    private Long scrapSourceId;
    private DocumentType documentType;
    private String dataPath;
}
