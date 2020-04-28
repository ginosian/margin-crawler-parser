package com.margin.transliteration.service.transliteration.impl;

import com.margin.enums.LanguageCode;
import com.margin.enums.LoadingStage;
import com.margin.error.ApiException;
import com.margin.languagedetection.service.languagedetection.LanguageDetectionService;
import com.margin.model.NLPDataResponse;
import com.margin.model.NLPRequest;
import com.margin.model.NLPResponse;
import com.margin.transliteration.service.nlptoolprovider.NLPToolProviderService;
import com.margin.transliteration.service.transliteration.TransliterationService;
import com.margin.transliteration.validation.TransliterationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

import static org.springframework.util.Assert.hasText;

@Service
public class TransliterationServiceImpl implements TransliterationService {

    @Autowired
    private NLPToolProviderService transliterationProvider;

    @Autowired
    TransliterationValidator validator;

    @Autowired
    private LanguageDetectionService languageDetectionService;

    @Override
    public NLPResponse transliterate(NLPRequest request) {
        hasText(request.getSourceText(), "Text for transliteration cannot be null or empty!");
        hasText(request.getTargetLanguageCode().getLanguageName(), "Target language cannot be null or empty!");
        NLPResponse response = new NLPResponse();
        NLPDataResponse localResponse = new NLPDataResponse();
        String transliterated = null;
        Set<LanguageCode> possibleLanguages = request.getSourcePossibleLanguages();
        LanguageCode sourceLanguage = request.getSourceLanguageCode();
        LanguageCode targetLang = request.getTargetLanguageCode();

        if (sourceLanguage != null) {
            LanguageCode sourceLang = sourceLanguage;
            transliterated = transliterationProvider.getTransliterator(sourceLang, targetLang).transliterate(request.getSourceText());
        } else if (!possibleLanguages.isEmpty()) {
//            notEmpty(possibleLanguages, "Neither exact nor possible languages are provided for source text!");
            transliterated = transliterate(request.getSourceText(), targetLang, possibleLanguages);
        } else {
            try {
                Set<LanguageCode> detectedLanguages = languageDetectionService.detectPossibleLanguages(request).getLocalResponse().getSourcePossibleLanguages();
                transliterated = transliterate(request.getSourceText(), targetLang, detectedLanguages);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ApiException("Failed to detect possible languages for the source text to transliterate!", LoadingStage.NLP_TRANSLITERATION);
            }
        }
        if (transliterated != null) validator.validateEN(request, transliterated);
        localResponse.setTransliteration(transliterated);
        response.setLocalResponse(localResponse);
        return response;
    }

    private String transliterate(String sourceText, LanguageCode targetLang ,Set<LanguageCode> languages) {
        String currTranslit = sourceText, transliterated = null;
        for (LanguageCode sourcePossibleLanguage : languages) {
            transliterated = transliterationProvider.getTransliterator(sourcePossibleLanguage, targetLang).transliterate(currTranslit);
            currTranslit = transliterated;
        }
        return transliterated;
    }
}
