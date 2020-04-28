package com.margin.repository;

import com.margin.entity.LanguageProfileEntity;
import com.margin.enums.LanguageCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageProfileRepository extends MongoRepository<LanguageProfileEntity, String> {
    List<LanguageProfileEntity> findAllByNGramsExists();

    LanguageProfileEntity findLanguageProfileEntityByLanguageCode(LanguageCode languageCode);
}
