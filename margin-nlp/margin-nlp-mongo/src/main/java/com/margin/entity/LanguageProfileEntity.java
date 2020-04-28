package com.margin.entity;

import com.margin.enums.LanguageCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@Document(collection = "language_profile")
public class LanguageProfileEntity {

    @Field("language")
    @Indexed(unique = true)
    private LanguageCode languageCode;

    @Field("NGrams")
    private String nGrams;


}
