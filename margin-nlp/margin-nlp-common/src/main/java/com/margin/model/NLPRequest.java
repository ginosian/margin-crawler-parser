package com.margin.model;

import com.margin.enums.Country;
import com.margin.enums.LanguageCode;
import com.margin.enums.ScriptCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class NLPRequest {
    private Long id;
    private String sourceText;
    private Country sourceCountry;
    private LanguageCode sourceLanguageCode;
    private Set<LanguageCode> sourcePossibleLanguages;
    private LanguageCode targetLanguageCode;
    private ScriptCode sourceScriptCode;
    private ScriptCode targetScriptCode;
}
