package com.margin.entity.thirdparty.source.entity.metadata.genderapi;

import com.margin.dto.nlp.thirdparty.source.AbstractThirdPartyMetadataModel;

public class GenderApiComMetadataModel extends AbstractThirdPartyMetadataModel {
    private String apiKey;
    private String url; // https://gender-api.com/get?key=%s&name=%s
    private String localeParameter; //&country=%s
}
