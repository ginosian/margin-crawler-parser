package com.margin.dto.nlp;

import com.margin.enums.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NLPDataResponseDTO {
    private Long id;
    private String sourceText;
    private Country sourceCountry;
    private LanguageCode sourceLanguageCode;
    private LanguageCode targetLanguageCode;
    private String transliteration;
    private String translation;
    private Gender gender;
    private NameType nameType;
    private EntityType entityType;
}
