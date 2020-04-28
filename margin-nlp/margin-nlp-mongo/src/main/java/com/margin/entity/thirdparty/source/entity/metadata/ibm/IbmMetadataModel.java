package com.margin.entity.thirdparty.source.entity.metadata.ibm;

import com.margin.dto.nlp.thirdparty.source.AbstractThirdPartyMetadataModel;

public class IbmMetadataModel extends AbstractThirdPartyMetadataModel {
    private String apiKey;
    private String version; //2018-05-01
    private String url; //https://gateway.watsonplatform.net/language-translator/api (should be changed based on our credentials)
}
