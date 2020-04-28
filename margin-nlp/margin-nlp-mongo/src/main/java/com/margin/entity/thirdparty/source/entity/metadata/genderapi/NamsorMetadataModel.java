package com.margin.entity.thirdparty.source.entity.metadata.genderapi;

import com.margin.dto.nlp.thirdparty.source.AbstractThirdPartyMetadataModel;

public class NamsorMetadataModel extends AbstractThirdPartyMetadataModel {
    private String url; // https://api.namsor.com/onomastics/api/json/gender/%s/""
    private String localePart; // /%s
}
