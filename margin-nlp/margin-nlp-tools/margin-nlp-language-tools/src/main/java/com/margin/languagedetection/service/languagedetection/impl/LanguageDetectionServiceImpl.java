package com.margin.languagedetection.service.languagedetection.impl;

import com.margin.enums.LanguageCode;
import com.margin.enums.LanguageDetectionProfile;
import com.margin.languagedetection.service.languagedetection.LanguageDetectionService;
import com.margin.model.NLPDataResponse;
import com.margin.model.NLPRequest;
import com.margin.model.NLPResponse;
import com.margin.transliteration.service.nlptoolprovider.NLPToolProviderService;
import com.optimaize.langdetect.DetectedLanguage;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.util.Assert.hasText;

@Service
public class LanguageDetectionServiceImpl implements LanguageDetectionService {

    @Autowired
    private NLPToolProviderService nlpToolProviderService;

    @Override
    public NLPResponse detectPossibleLanguages(NLPRequest request) throws IOException {
        String sourceText = request.getSourceText();
        hasText(request.getSourceText(), "Text for language detection cannot be null or empty!");
        LanguageDetector languageDetector = nlpToolProviderService.getLanguageDetector(LanguageDetectionProfile.LOCAL);
        NLPResponse nlpResponse = new NLPResponse();
        NLPDataResponse nlpLocalResponse = new NLPDataResponse();
        Set<LanguageCode> possibleLangs = new HashSet<>();
        TextObjectFactory textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();
        TextObject textObject = textObjectFactory.forText(sourceText);
        List<DetectedLanguage> lngList = languageDetector.getProbabilities(textObject);
        for (DetectedLanguage detectedLanguage : lngList) {
            possibleLangs.add(LanguageCode.valueOf(detectedLanguage.getLocale().getLanguage()));
        }
        nlpLocalResponse.setSourceText(sourceText);
        nlpLocalResponse.setSourcePossibleLanguages(possibleLangs);
        nlpResponse.setLocalResponse(nlpLocalResponse);
        return nlpResponse;
    }
}
