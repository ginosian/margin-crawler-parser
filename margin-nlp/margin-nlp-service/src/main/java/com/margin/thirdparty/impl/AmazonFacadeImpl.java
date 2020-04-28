package com.margin.thirdparty.impl;

import com.margin.MarginExceptionHelper;
import com.margin.mapper.BeanMapper;
import com.margin.service.amazon.AmazonServiceImpl;
import com.margin.thirdparty.ThirdPartyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Fixme make this general, in your module get a call from this and there resolve which third party provider to use for task
@Service
public class AmazonFacadeImpl implements ThirdPartyFacade {


    @Autowired
    //@Fixme never inject via impl, use @Qualifier instead and inject via interface, this way your'e forcing cglibs reading which is bad
    private AmazonServiceImpl service;

    @Autowired
    private BeanMapper mapper;

    @Autowired
    private MarginExceptionHelper helper;

//    @Override
//    public GenericResponseDTO<LanguageDTO> detectLanguage(LanguageDTO languageDTO) {
//        notNull(languageDTO, "DTO for language detection can not be null.");
//        notNull(languageDTO.getSourceText(), "Source text in DTO for language detection can not be null.");
//        final LanguageDetectionRequest request = mapper.map(languageDTO, LanguageDetectionRequest.class);
//        GenericResponseDTO<LanguageDTO> response;
//        try {
//            final LanguageDetectionResponse serviceResponse = service.detectLanguage(request);
//            response = new GenericResponseDTO<>(null, mapper.map(serviceResponse, LanguageDTO.class));
//        } catch (Exception e) {
//            response = new GenericResponseDTO<>(helper.resolve(e, LoadingStage.NLP_THIRD_PARTY), null);
//        }
//        return response;
//    }
//
//    @Override
//    public GenericResponseDTO<TextDTO> translate(TextDTO textDTO) {
//        notNull(textDTO, "DTO for translation can not be null.");
//        notNull(textDTO.getSourceText(), "Source text in DTO for translation can not be null.");
//        final TranslationRequest request = mapper.map(textDTO, TranslationRequest.class);
//        GenericResponseDTO<TextDTO> response;
//        try {
//            final ThirdPartyResponse serviceResponse = service.translate(request);
//            response = new GenericResponseDTO<>(null, mapper.map(serviceResponse, TextDTO.class));
//        } catch (Exception e) {
//            response = new GenericResponseDTO<>(helper.resolve(e, LoadingStage.NLP_THIRD_PARTY), null);
//        }
//        return response;
//    }
}