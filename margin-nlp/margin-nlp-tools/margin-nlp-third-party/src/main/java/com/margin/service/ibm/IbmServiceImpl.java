package com.margin.service.ibm;

import com.margin.enums.LanguageCode;
import com.margin.model.NLPDataResponse;
import com.margin.model.NLPRequest;
import com.margin.service.LanguageThirdPartyService;
import com.margin.service.ibm.client.IbmClientProvider;
import com.ibm.watson.developer_cloud.language_translator.v3.model.IdentifiedLanguages;
import com.ibm.watson.developer_cloud.language_translator.v3.model.IdentifyOptions;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslationResult;
import model.NLPLanguageDetectionRequest;
import model.NLPLanguageDetectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.notNull;

/**
 * https://www.ibm.com/watson/developercloud/language-translator/api/v3/java.html?java#sync
 */

@Service
public class IbmServiceImpl implements LanguageThirdPartyService {
    @Autowired
    private IbmClientProvider clientProvider;

    @Override
    public NLPLanguageDetectionResponse detectLanguage(NLPLanguageDetectionRequest request) {
        notNull(request, "Request for IBM language detection can not be null.");
        final String sourceText = request.getSourceText();
        notNull(sourceText, "Text for IBM language detection can not be null.");

        IdentifyOptions identifyOptions = new IdentifyOptions.Builder()
                .text(sourceText)
                .build();
        IdentifiedLanguages languages = clientProvider.getTranslateClient().identify(identifyOptions).execute();
        final String detectedLanguageCode = languages.getLanguages().get(0).getLanguage().split("-")[0];

        NLPLanguageDetectionResponse response = new NLPLanguageDetectionResponse();
        response.setDetectedLanguageCode(LanguageCode.valueOf(detectedLanguageCode));
        return response;
    }

    @Override
    public NLPDataResponse translate(NLPRequest request) {
        notNull(request, "Request for IBM translation can not be null.");
        notNull(request.getSourceText(), "Text for IBM translation can not be null.");
        notNull(request.getTargetLanguageCode(), "Target language for IBM translation can not be null.");

        final LanguageCode sourceLanguageCode = request.getSourceLanguageCode();
        if (sourceLanguageCode == null) {
            NLPLanguageDetectionRequest languageDetectionRequest = new NLPLanguageDetectionRequest();
            languageDetectionRequest.setSourceText(request.getSourceText());
            final NLPLanguageDetectionResponse languageDetectionResponse = detectLanguage(languageDetectionRequest);
            final LanguageCode detectedLanguageCode = languageDetectionResponse.getDetectedLanguageCode();
            notNull(detectedLanguageCode, "IBM detected language can not be null.");

            return translate(request, detectedLanguageCode);
        }
        return translate(request, sourceLanguageCode);

    }

    private NLPDataResponse translate(NLPRequest request, LanguageCode sourceLanguageCode) {
        final String sourceText = request.getSourceText();
        final LanguageCode targetLanguageCode = request.getTargetLanguageCode();
        TranslateOptions translateOptions = new TranslateOptions.Builder()
                .addText(sourceText)
                .source(sourceLanguageCode.name())
                .target(targetLanguageCode.name())
                .build();
        TranslationResult result = clientProvider.getTranslateClient().translate(translateOptions)
                .execute();

        final NLPDataResponse response = new NLPDataResponse();
        response.setSourceText(sourceText);
        response.setSourceLanguageCode(sourceLanguageCode);
        response.setTargetLanguageCode(targetLanguageCode);
        response.setTranslation(result.getTranslations().get(0).getTranslationOutput());

        return response;
    }
}
