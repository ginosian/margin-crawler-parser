package com.margin.languagetools.impl;

import com.margin.MarginExceptionHelper;
import com.margin.dto.GenericResponseDTO;
import com.margin.dto.nlp.NLPRequestDTO;
import com.margin.dto.nlp.NLPResponseDTO;
import com.margin.enums.LoadingStage;
import com.margin.languagetools.LanguageToolsFacade;
import com.margin.mapper.BeanMapper;
import com.margin.model.NLPRequest;
import com.margin.model.NLPResponse;
import com.margin.transliteration.service.transliteration.TransliterationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.notNull;

@Service
public class LanguageToolsFacadeImpl implements LanguageToolsFacade {
    @Autowired
    private TransliterationService service;

    @Autowired
    private BeanMapper mapper;

    @Autowired
    private MarginExceptionHelper helper;

    @Override
    public GenericResponseDTO<NLPResponseDTO> transliterate(NLPRequestDTO nlpDTO) {
        notNull(nlpDTO, "Text DTO for transliteration cannot be null!");
        final NLPRequest request = mapper.map(nlpDTO, NLPRequest.class);
        GenericResponseDTO<NLPResponseDTO> response;
        try {
            final NLPResponse serviceResponse = service.transliterate(request);
            response = new GenericResponseDTO<>(null, mapper.map(serviceResponse, NLPResponseDTO.class));
        } catch (Exception e) {
            response = new GenericResponseDTO<>(helper.resolve(e, LoadingStage.NLP_LANGUAGE_TOOLS), null);
        }
        return response;
    }
}
