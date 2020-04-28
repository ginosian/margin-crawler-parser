package com.margin.transliteration.service.nlptoolprovider.impl;


import com.margin.entity.LanguageProfileEntity;
import com.margin.entity.TransliterationRuleEntity;
import com.margin.enums.LanguageCode;
import com.margin.enums.LanguageDetectionProfile;
import com.margin.enums.LoadingStage;
import com.margin.error.ApiException;
import com.margin.repository.LanguageProfileRepository;
import com.margin.repository.TransliterationRuleRepository;
import com.margin.transliteration.service.nlptoolprovider.NLPToolProviderService;
import com.ibm.icu.text.Transliterator;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EnableCaching
@Service
public class NLPToolProviderServiceImpl implements NLPToolProviderService {

    @Autowired
    private TransliterationRuleRepository transliterationRulesRepository;

    @Autowired
    private LanguageProfileRepository languageProfileRepository;

    @Cacheable(value = "transliterator", key = "{#fromLngId, #toLngId}")
    @Override
    public Transliterator getTransliterator(LanguageCode fromLngId, LanguageCode toLngId) {
        TransliterationRuleEntity ruleEntity = transliterationRulesRepository.findTransliterationRulesEntityBySourceLanguageAndTargetLanguage(fromLngId, toLngId);
        if (ruleEntity == null) {
            ApiException e = new ApiException("Transliterator not found from language " + fromLngId + " to language " + toLngId);
            e.setStage(LoadingStage.NLP_TRANSLITERATION);
            throw e;
        }
        String rules = ruleEntity.getRules();
        return Transliterator.createFromRules("polixis", rules, Transliterator.FORWARD);
    }




    @Cacheable(value = "language_detector", key = "#languageDetectionProfile")
    @Override
    public LanguageDetector getLanguageDetector(LanguageDetectionProfile languageDetectionProfile) throws IOException{
        List<LanguageProfile> languageProfiles = new ArrayList<>();
        switch (languageDetectionProfile) {
            case LOCAL:
                for (LanguageProfileEntity langProfile : languageProfileRepository.findAllByNGramsExists()) {
                    InputStream is = new ByteArrayInputStream(langProfile.getNGrams().getBytes());
                    languageProfiles.add(new LanguageProfileReader().read(is));
                }
                break;
            case ALL_BUILT_IN:
                languageProfiles = new LanguageProfileReader().readAllBuiltIn();
                break;
        }
        if (languageProfiles.isEmpty()) throw new ApiException("Language detector failed to initialize!", LoadingStage.NLP_LANGUAGE_TOOLS);
        else
            return LanguageDetectorBuilder
                        .create(NgramExtractors.standard())
                        .withProfiles(languageProfiles)
                        .build();
    }

    @Override
    public void populateRulesFromFolder(String rulesFolderPath) throws IOException {
        File rulesFolder = new File(rulesFolderPath);
        for (File file : Objects.requireNonNull(rulesFolder.listFiles())) {
            if (file.isDirectory()) continue;
            String fileName = file.getName();
            if (!fileName.endsWith(".rul")) continue;
            if (fileName.contains(".alternative")) continue;
            String[] langs = fileName.replace(".rul", "").split("-");
            String rules = new String(Files.readAllBytes(file.toPath()));
            TransliterationRuleEntity rulesEntity = new TransliterationRuleEntity();
            rulesEntity.setRules(rules);
            rulesEntity.setSourceLanguage(LanguageCode.valueOf(langs[0]));
            rulesEntity.setTargetLanguage(LanguageCode.valueOf(langs[1]));
            transliterationRulesRepository.save(rulesEntity);
        }
    }
}

