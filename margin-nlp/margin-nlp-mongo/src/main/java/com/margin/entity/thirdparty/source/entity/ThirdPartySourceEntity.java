package com.margin.entity.thirdparty.source.entity;

import com.margin.dto.nlp.thirdparty.source.AbstractThirdPartyMetadataModel;
import com.margin.enums.CrawllingType;
import com.margin.enums.LanguageCode;
import com.margin.enums.NLPServiceType;
import com.margin.enums.NLPThirdParty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Document(collection = "third_party_source")
public class ThirdPartySourceEntity{

    @Id
    private String _id;

    @Field(value = "name")
    @Indexed
    private NLPThirdParty name;

    @Field(value = "url")
    private String url;

    @Field(value = "description")
    private String description;

    @Field(value = "services")
    private Set<NLPServiceType> serviceTypes = new HashSet<>();

    @Field(value = "supported_languages")
    private Set<LanguageCode> languages;

    @Field(value = "crawling_type")
    private CrawllingType crawllingType;

    @Field(value = "threshold")
    private APIThresholdInfoEntity threshold;

    @Field(value = "metadata")
    private AbstractThirdPartyMetadataModel metadata;
}
