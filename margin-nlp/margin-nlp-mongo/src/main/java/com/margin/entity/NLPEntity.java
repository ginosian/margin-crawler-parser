package com.margin.entity;

import com.margin.enums.Country;
import com.margin.enums.EntityType;
import com.margin.enums.LanguageCode;
import com.margin.enums.NLPVerificationSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class NLPEntity {
    @Field(value = "_id")
    private String _id;

    @Field(value = "name")
    private String name;

    @Field(value = "language")
    private LanguageCode language;

    @Field(value = "country")
    private Country country;

    @Field(value = "type")
    private EntityType entityType;

    @Field(value = "verification_source")
    private NLPVerificationSource verificationSource;

    @Field(value = "transliteration_english")
    private String transliterationEnglish;

    @Field(value = "translation_english")
    private String translationEnglish;

    @Field(value = "machine_learned")
    private Boolean machineLearned;
}
