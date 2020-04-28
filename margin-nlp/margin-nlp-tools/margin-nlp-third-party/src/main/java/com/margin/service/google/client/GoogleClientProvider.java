package com.margin.service.google.client;

import com.margin.error.ApiException;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Component
public class GoogleClientProvider {
    public Translate getTranslateClient() {
        try {
            return TranslateOptions.newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("third.party/google-client.json")))
                    .build()
                    .getService();
        } catch (IOException e) {
            throw new ApiException(e.getMessage());
        }
    }
}
