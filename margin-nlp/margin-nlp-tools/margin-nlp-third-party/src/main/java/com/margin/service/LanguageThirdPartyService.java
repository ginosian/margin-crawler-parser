package com.margin.service;

import com.margin.model.NLPDataResponse;
import com.margin.model.NLPRequest;
import model.NLPLanguageDetectionRequest;
import model.NLPLanguageDetectionResponse;

public interface LanguageThirdPartyService {
    default NLPLanguageDetectionResponse detectLanguage(NLPLanguageDetectionRequest request) {
        return null;
    }

    default NLPDataResponse transliterate(NLPRequest request) {
        return null;
    }

    default NLPDataResponse translate(NLPRequest request) {
        return null;
    }
}
