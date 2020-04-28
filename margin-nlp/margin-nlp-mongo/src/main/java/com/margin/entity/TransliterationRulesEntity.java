package com.margin.entity;

import com.margin.enums.LanguageCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@Document(collection = "transliteration_rules")
public class TransliterationRulesEntity {
    @Id
    private String _id;

    @Field
    @Indexed(unique = true)
    private LanguageCode sourceLanguage;

    @Field
    @Indexed(unique = true)
    private LanguageCode targetLanguage;

    @Field
    private String rules;
}
