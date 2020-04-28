package com.margin.dto.nlp;

import com.margin.enums.Country;
import com.margin.enums.LanguageCode;
import com.margin.enums.NLPServiceType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class NLPRequestDTO {
    private Long id;
    private String sourceText;
    private Country sourceCountry;
    private LanguageCode sourceLanguageCode;
    private LanguageCode targetLanguageCode;
    private Set<LanguageCode> sourcePossibleLanguages;
    private List<NLPServiceType> targetServices;
}
