package com.margin.model;

import com.margin.enums.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class NLPDataResponse {
    private Long id;
    private String sourceText;
    private Country sourceCountry;
    private LanguageCode sourceLanguageCode;
    private Set<LanguageCode> sourcePossibleLanguages;
    private LanguageCode targetLanguageCode;
    private ScriptCode sourceScriptCode;
    private ScriptCode targetScriptCode;
    private String transliteration;
    private String translation;
    private Gender gender;
    private NameType nameType;
    private EntityType entityType;
}
