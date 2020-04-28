package com.margin.languagedetection.service.languagedetection;

import com.margin.model.NLPRequest;
import com.margin.model.NLPResponse;

import java.io.IOException;

public interface LanguageDetectionService {
    NLPResponse detectPossibleLanguages(NLPRequest request) throws IOException;
}
