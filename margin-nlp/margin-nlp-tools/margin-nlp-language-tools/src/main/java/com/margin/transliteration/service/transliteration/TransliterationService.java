package com.margin.transliteration.service.transliteration;

import com.margin.model.NLPRequest;
import com.margin.model.NLPResponse;

public interface TransliterationService {
    NLPResponse transliterate(NLPRequest request);
}
