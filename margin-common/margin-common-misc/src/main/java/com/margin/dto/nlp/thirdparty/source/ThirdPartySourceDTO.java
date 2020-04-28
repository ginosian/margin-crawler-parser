package com.margin.dto.nlp.thirdparty.source;

import com.margin.enums.CrawllingType;
import com.margin.enums.NLPServiceType;
import com.margin.enums.NLPThirdParty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ThirdPartySourceDTO {
    private Long id;
    private NLPThirdParty name;
    private String url;
    private String description;
    private Set<NLPServiceType> serviceTypes;
    private CrawllingType crawllingType;
    private String loaderClassName;
    private APIThresholdInfoDTO thresholdInfo;
}
