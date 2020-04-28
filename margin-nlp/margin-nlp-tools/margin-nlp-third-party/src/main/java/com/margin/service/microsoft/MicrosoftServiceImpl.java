package com.margin.service.microsoft;

import com.margin.enums.LanguageCode;
import com.margin.enums.NLPThirdParty;
import com.margin.enums.ScriptCode;
import com.margin.error.ApiException;
import com.margin.model.NLPDataResponse;
import com.margin.model.NLPRequest;
import com.margin.service.LanguageThirdPartyService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import model.NLPLanguageDetectionRequest;
import model.NLPLanguageDetectionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.springframework.util.Assert.notNull;

@Service
@PropertySource("classpath:third.party/microsoft-nlp.properties")
public class MicrosoftServiceImpl implements LanguageThirdPartyService {
    @Value("subscription.key")
    private String subscriptionKey;

    @Value("host")
    private String host;

    @Value("detect.language.path")
    private String languageDetectionPath;

    @Value("translate.path")
    private String translationPath;

    @Value("transliterate.path")
    private String transliterationPath;

    private OkHttpClient client = new OkHttpClient();

    @Override
    public NLPLanguageDetectionResponse detectLanguage(NLPLanguageDetectionRequest request) {
        notNull(request, "Request for language detection can not be null.");
        notNull(request.getSourceText(), "Source text for language detection can not be null.");
        final String url = host + languageDetectionPath;
        final String responseBody = getResponseBody(request.getSourceText(), url);

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
        final String language = jsonObject.get("language").getAsString();
        NLPLanguageDetectionResponse response = new NLPLanguageDetectionResponse();
        response.setDetectedLanguageCode(LanguageCode.valueOf(language));
        return response;
    }

    @Override
    public NLPDataResponse translate(NLPRequest request) {
        notNull(request, "Request for translation can not be null.");
        notNull(request.getSourceText(), "Text for translation can not be null.");
        notNull(request.getTargetLanguageCode(), "Target Language code for translation can not be null.");

        final String url = host + translationPath + "&to=" + request.getTargetLanguageCode();
        final String responseBody = getResponseBody(request.getSourceText(), url);

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
        final String translation = jsonObject.get("translation").getAsJsonObject().get("text").getAsString();
        final String sourceLanguageCode = jsonObject.get("detectedLanguage").getAsJsonObject().get("language").getAsString();

        NLPDataResponse response = new NLPDataResponse();
        response.setSourceText(request.getSourceText());
        response.setTranslation(translation);
        response.setTargetLanguageCode(request.getTargetLanguageCode());
        response.setSourceLanguageCode(LanguageCode.valueOf(sourceLanguageCode));

        return response;
    }

    @Override
    public NLPDataResponse transliterate(NLPRequest request) {
        notNull(request, "Request for transliteration can not be null.");
        final String sourceText = request.getSourceText();
        notNull(sourceText, "Source text for transliteration can not be null.");
        final LanguageCode sourceLanguageCode = request.getSourceLanguageCode();
        notNull(sourceLanguageCode, "Source language code for transliteration can not be null.");
        final ScriptCode sourceScriptCode = request.getSourceScriptCode();
        notNull(sourceScriptCode, "Source script code for transliteration can not be null.");
        final ScriptCode targetScriptCode = request.getTargetScriptCode();
        notNull(targetScriptCode, "Target script code for transliteration can not be null.");

        final String url = host + transliterationPath +
                "&language=" + sourceLanguageCode +
                "&fromScript=" + sourceScriptCode +
                "&toScript=" + targetScriptCode;
        final String responseBody = getResponseBody(sourceText, url);

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
        final String transliteration = jsonObject.get("text").getAsString();
        final String script = jsonObject.get("script").getAsString();

        NLPDataResponse response = new NLPDataResponse();
        response.setSourceText(sourceText);
        response.setTransliteration(transliteration);
        response.setSourceScriptCode(sourceScriptCode);
        response.setTargetScriptCode(ScriptCode.valueOf(script));
        response.setSourceLanguageCode(sourceLanguageCode);

        return response;
    }

    private String getResponseBody(String sourceText, String url) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
                "[{\n\t\"Text\": \"" + sourceText + "\"\n}]");
        Request innerRequest = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                .addHeader("Content-type", "application/json")
                .build();
        final String responseBody;
        try {
            Response response = client.newCall(innerRequest).execute();
            responseBody = response.body().string();
        } catch (IOException e) {
            throw new ApiException(NLPThirdParty.AMAZON + e.getMessage());
        }
        return responseBody;
    }
}
