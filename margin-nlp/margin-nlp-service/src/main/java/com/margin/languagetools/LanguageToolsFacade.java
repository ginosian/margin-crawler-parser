package com.margin.languagetools;

import com.margin.dto.GenericResponseDTO;
import com.margin.dto.nlp.NLPRequestDTO;
import com.margin.dto.nlp.NLPResponseDTO;

public interface LanguageToolsFacade {
    GenericResponseDTO<NLPResponseDTO> transliterate(NLPRequestDTO request);
}
