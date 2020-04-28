package com.margin.service.model.scrap_action_details;

import com.margin.enums.Status;
import com.margin.service.model.SourceMetaDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScrapActionDetailsSearchRequest {
    private SourceMetaDataModel sourceMetaData;
    private Integer version;
    private Status status;
}
