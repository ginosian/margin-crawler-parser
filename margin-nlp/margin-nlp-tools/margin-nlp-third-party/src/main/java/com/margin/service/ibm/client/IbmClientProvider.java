package com.margin.service.ibm.client;

import com.ibm.watson.developer_cloud.language_translator.v3.LanguageTranslator;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:third.party/ibm-nlp.properties")
public class IbmClientProvider {
    public LanguageTranslator getTranslateClient() {
        IamOptions options = new IamOptions.Builder()
                .apiKey("{apikey}")
                .build();

        LanguageTranslator languageTranslator = new LanguageTranslator("{version}", options);

        languageTranslator.setEndPoint("{url}");
        return languageTranslator;
    }

}
