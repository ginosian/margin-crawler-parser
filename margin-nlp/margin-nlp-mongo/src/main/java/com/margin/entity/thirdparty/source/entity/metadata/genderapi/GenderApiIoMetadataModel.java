package com.margin.entity.thirdparty.source.entity.metadata.genderapi;

import com.margin.dto.nlp.thirdparty.source.AbstractThirdPartyMetadataModel;

public class GenderApiIoMetadataModel extends AbstractThirdPartyMetadataModel {
    private String apiKey;
    private String url; //https://genderapi.io/api/?name=%s&key=%s"
}
