package com.margin.service.selenium;

import com.margin.model.NLPDataResponse;
import com.margin.model.NLPRequest;
import com.margin.service.LanguageThirdPartyService;
import model.NLPLanguageDetectionRequest;
import model.NLPLanguageDetectionResponse;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:third.party/selenium-nlp.properties")
public class SeleniumServiceImpl implements LanguageThirdPartyService {

    @Override
    public NLPLanguageDetectionResponse detectLanguage(NLPLanguageDetectionRequest request) {
        return null;
    }

    @Override
    public NLPDataResponse transliterate(NLPRequest request) {
        return null;
    }

    @Override
    public NLPDataResponse translate(NLPRequest request) {
        return null;
    }
}