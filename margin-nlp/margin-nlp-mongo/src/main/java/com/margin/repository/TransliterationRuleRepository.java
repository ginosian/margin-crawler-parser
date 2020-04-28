package com.margin.repository;

import com.margin.entity.TransliterationRuleEntity;
import com.margin.enums.LanguageCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransliterationRuleRepository extends MongoRepository<TransliterationRuleEntity, String> {
    TransliterationRuleEntity findTransliterationRulesEntityBySourceLanguageAndTargetLanguage(LanguageCode sourceLanguage, LanguageCode targetLanguage);
}
