package com.margin.service.amazon;

import com.amazonaws.services.comprehend.model.DetectDominantLanguageRequest;
import com.amazonaws.services.comprehend.model.DetectDominantLanguageResult;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import com.margin.enums.LanguageCode;
import com.margin.error.ApiException;
import com.margin.misc.AmazonSupportedLanguage;
import com.margin.model.NLPDataResponse;
import com.margin.model.NLPRequest;
import com.margin.service.LanguageThirdPartyService;
import com.margin.service.amazon.client.AmazonClientProvider;
import model.NLPLanguageDetectionRequest;
import model.NLPLanguageDetectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

import static org.springframework.util.Assert.notNull;

@Service
public class AmazonServiceImpl implements LanguageThirdPartyService {
    @Autowired
    private AmazonClientProvider clientProvider;

    @Override
    public NLPLanguageDetectionResponse detectLanguage(NLPLanguageDetectionRequest request) {
        notNull(request, "Request for language detection can not be null.");
        notNull(request.getSourceText(), "Text for language detection can not be null.");
        final DetectDominantLanguageRequest detectDominantLanguageRequest = new DetectDominantLanguageRequest()
                .withText(request.getSourceText());
        final DetectDominantLanguageResult detectDominantLanguageResult = clientProvider.getComprehendClient()
                .detectDominantLanguage(detectDominantLanguageRequest);
        notNull(detectDominantLanguageResult, "Language detection result can not be null.");
        final String languageCode = detectDominantLanguageResult.getLanguages().get(0).getLanguageCode();
        final LanguageCode detectedLanguageCode = LanguageCode.valueOf(languageCode);

        final NLPLanguageDetectionResponse response = new NLPLanguageDetectionResponse();
        response.setDetectedLanguageCode(detectedLanguageCode);
        return response;
    }

    @Override
    public NLPDataResponse translate(NLPRequest request) {
        notNull(request, "Request for translation can not be null.");
        final String sourceText = request.getSourceText();
        notNull(sourceText, "Text for translation can not be null.");
        final LanguageCode targetLanguageCode = request.getTargetLanguageCode();
        notNull(targetLanguageCode, "Target language code for translation can not be null.");
        final LanguageCode sourceLanguageCode = request.getSourceLanguageCode();
        if (sourceLanguageCode == null) {
            final NLPLanguageDetectionRequest languageDetectionRequest = new NLPLanguageDetectionRequest();
            languageDetectionRequest.setSourceText(sourceText);
            final LanguageCode detectedLanguageCode = detectLanguage(languageDetectionRequest).getDetectedLanguageCode();
            notNull(detectedLanguageCode, "Language detection result can not be null.");
            return translate(sourceText, detectedLanguageCode, targetLanguageCode);
        }
        return translate(sourceText, sourceLanguageCode, targetLanguageCode);
    }

    private NLPDataResponse translate(String sourceText,
                                      LanguageCode sourceLanguageCode,
                                      LanguageCode targetLanguageCode) {
        NLPDataResponse response = new NLPDataResponse();
        response.setSourceLanguageCode(sourceLanguageCode);
        response.setSourceText(sourceText);

        if (targetLanguageCode == sourceLanguageCode) {
            response.setTranslation(sourceText);
            response.setTargetLanguageCode(sourceLanguageCode);
            return response;
        }
        if (isLanguageSupported(sourceLanguageCode.name())) {
            TranslateTextRequest translationRequest = new TranslateTextRequest()
                    .withText(sourceText)
                    .withSourceLanguageCode(sourceLanguageCode.name())
                    .withTargetLanguageCode(targetLanguageCode.name());
            TranslateTextResult result = clientProvider.getTranslatorClient().translateText(translationRequest);
            notNull(result, "Translation result can not be null.");
            response.setTranslation(result.getTranslatedText());
            response.setTargetLanguageCode(targetLanguageCode);
            return response;
        }
        //@Fixme concat AMAZON enum to exception message
        throw new ApiException("The source language is not supported by Amazon Translate Client");
    }

    private boolean isLanguageSupported(String sourceLanguageCode) {
        return EnumSet.allOf(AmazonSupportedLanguage.class).contains(sourceLanguageCode);
    }
}
