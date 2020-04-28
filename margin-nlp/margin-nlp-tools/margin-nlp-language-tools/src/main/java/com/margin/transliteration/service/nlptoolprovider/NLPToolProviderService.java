package com.margin.transliteration.service.nlptoolprovider;

import com.margin.enums.LanguageCode;
import com.margin.enums.LanguageDetectionProfile;
import com.ibm.icu.text.Transliterator;
import com.optimaize.langdetect.LanguageDetector;

import java.io.IOException;

public interface NLPToolProviderService {
    Transliterator getTransliterator(LanguageCode fromLngId, LanguageCode toLngId);

    LanguageDetector getLanguageDetector(LanguageDetectionProfile languageDetectionProfile) throws IOException;

    void populateRulesFromFolder(String rulesFolderPath) throws IOException;
}
