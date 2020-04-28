package com.margin.entity;

import com.margin.enums.LanguageCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@Document(collection = "transliteration_rules")
@CompoundIndexes({
        @CompoundIndex(
                name = "from_to",
                def = "{'source_language' : 1, 'target_language': 1}",
                unique = true,
                dropDups = true)
})
public class TransliterationRuleEntity {
    @Id
    private String _id;

    @Field("source_language")
    private LanguageCode sourceLanguage;

    @Field("target_language")
    private LanguageCode targetLanguage;

    @Field
    private String rules;
}
