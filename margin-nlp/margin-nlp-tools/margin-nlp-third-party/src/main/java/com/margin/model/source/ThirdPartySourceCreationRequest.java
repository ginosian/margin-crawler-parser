package com.margin.model.source;

import com.margin.dto.nlp.thirdparty.source.AbstractThirdPartyMetadataModel;
import com.margin.enums.CrawllingType;
import com.margin.enums.LanguageCode;
import com.margin.enums.NLPServiceType;
import com.margin.enums.NLPThirdParty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ThirdPartySourceCreationRequest {
    private NLPThirdParty name;
    private String url;
    private String description;
    private Set<NLPServiceType> serviceTypes;
    private CrawllingType crawllingType;
    private APIThresholdInfoModel threshold;
    private Set<LanguageCode> languages;
    private AbstractThirdPartyMetadataModel metadata;
}
