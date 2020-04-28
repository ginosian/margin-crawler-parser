package com.margin.entity.thirdparty.source.entity.metadata.genderapi;

import com.margin.dto.nlp.thirdparty.source.AbstractThirdPartyMetadataModel;

public class GenderizeMetadataModel extends AbstractThirdPartyMetadataModel {
    private String url; // https://api.genderize.io/?name=%s
    private String localeParameter; // &country_id=%s
}
