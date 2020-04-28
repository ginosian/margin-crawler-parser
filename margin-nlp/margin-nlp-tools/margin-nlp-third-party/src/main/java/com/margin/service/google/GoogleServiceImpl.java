package com.margin.service.google;

import com.margin.enums.LanguageCode;
import com.margin.model.NLPDataResponse;
import com.margin.model.NLPRequest;
import com.margin.service.LanguageThirdPartyService;
import com.margin.service.google.client.GoogleClientProvider;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translation;
import model.NLPLanguageDetectionRequest;
import model.NLPLanguageDetectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.notNull;

/**
 * https://github.com/googleapis/google-api-java-client/issues/243
 * https://github.com/googlearchive/gwt-google-apis
 * https://developers.google.com/transliterate/v1/getting_started
 */

@Service
public class GoogleServiceImpl implements LanguageThirdPartyService {
    @Autowired
    private GoogleClientProvider clientProvider;

    @Override
    public NLPLanguageDetectionResponse detectLanguage(NLPLanguageDetectionRequest request) {
        notNull(request, "Request for Google language detection can not be null.");
        notNull(request.getSourceText(), "Text for Google language detection can not be null.");
        Detection detection = clientProvider.getTranslateClient().detect(request.getSourceText());

        NLPLanguageDetectionResponse response = new NLPLanguageDetectionResponse();
        response.setDetectedLanguageCode(LanguageCode.valueOf(detection.getLanguage()));
        return response;
    }

    @Override
    public NLPDataResponse translate(NLPRequest request) {
        notNull(request, "Request for Google translation can not be null.");
        notNull(request.getSourceText(), "Text for Google translation can not be null.");
        notNull(request.getTargetLanguageCode(), "Target language for Google translation can not be null.");

        final LanguageCode sourceLanguageCode = request.getSourceLanguageCode();
        if (sourceLanguageCode == null) {
            NLPLanguageDetectionRequest languageDetectionRequest = new NLPLanguageDetectionRequest();
            languageDetectionRequest.setSourceText(request.getSourceText());
            final NLPLanguageDetectionResponse languageDetectionResponse = detectLanguage(languageDetectionRequest);
            final LanguageCode detectedLanguageCode = languageDetectionResponse.getDetectedLanguageCode();
            notNull(detectedLanguageCode, "Google detected language can not be null.");

            return translate(request, detectedLanguageCode);
        }
        return translate(request, sourceLanguageCode);
    }

    private NLPDataResponse translate(NLPRequest request, LanguageCode sourceLanguageCode) {
        final String sourceText = request.getSourceText();
        final LanguageCode targetLanguageCode = request.getTargetLanguageCode();
        Translation translation = clientProvider.getTranslateClient().translate(
                sourceText,
                Translate.TranslateOption.sourceLanguage(sourceLanguageCode.name()),
                Translate.TranslateOption.targetLanguage(targetLanguageCode.name()));

        NLPDataResponse response = new NLPDataResponse();
        response.setSourceText(sourceText);
        response.setSourceLanguageCode(sourceLanguageCode);
        response.setTargetLanguageCode(targetLanguageCode);
        response.setTranslation(translation.getTranslatedText());

        return response;
    }
}
