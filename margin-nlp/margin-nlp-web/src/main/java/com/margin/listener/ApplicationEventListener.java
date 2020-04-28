package com.margin.listener;

import com.margin.dto.GenericResponseDTO;
import com.margin.dto.nlp.NLPRequestDTO;
import com.margin.dto.nlp.NLPResponseDTO;
import com.margin.dto.nlp.genderdetector.GenderDetectorCreateRequestDTO;
import com.margin.enums.Country;
import com.margin.enums.CrawllingType;
import com.margin.enums.Gender;
import com.margin.enums.LanguageCode;
import com.margin.enums.NLPServiceType;
import com.margin.enums.NLPThirdParty;
import com.margin.enums.NLPVerificationSource;
import com.margin.enums.NameType;
import com.margin.languagetools.LanguageToolsFacade;
import com.margin.model.source.ThirdPartySourceCreationRequest;
import com.margin.model.source.ThirdPartySourceResponse;
import com.margin.service.source.ThirdPartySourceService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ApplicationEventListener {

//    @Autowired
//    private AmazonFacadeImpl facade;
//
//    @Autowired
//    private LanguageToolsFacade facade;


    @Autowired
    private LanguageToolsFacade facade;

    @Autowired
    private ThirdPartySourceService sourceService;





    @EventListener({ContextRefreshedEvent.class})
    public void onContextRefreshedEvent() {
        testThirdPartyService();
        testGenderDetector();
        System.out.println();
        testLanguageTools("Привет ապեր", LanguageCode.en, LanguageCode.ru, LanguageCode.hy);

        testLanguageTools(" ապեր Пр ապերивеապերтապեր ",LanguageCode.en, LanguageCode.ru, LanguageCode.hy);
    }




    public void testLanguageTools(String text, LanguageCode to, LanguageCode ... from) {
        NLPRequestDTO nlpDTO = new NLPRequestDTO();
        nlpDTO.setSourceText(text);
        nlpDTO.setTargetLanguageCode(to);
        Set<LanguageCode> languageCodes = Arrays.stream(from).collect(Collectors.toSet());
        if (languageCodes.size() == 1) nlpDTO.setTargetLanguageCode(languageCodes.iterator().next());
        else {
            nlpDTO.setSourcePossibleLanguages(languageCodes);
        }
        final GenericResponseDTO<NLPResponseDTO> responseDTO = facade.transliterate(nlpDTO);

        System.out.println(responseDTO.getResponse().getLocalResponse().getTransliteration());
    }
//
//    public void testThirdParty(){
//        LanguageDTO languageDTO = new LanguageDTO();
//        languageDTO.setSourceText("Будь ласка");
//        TextDTO textDTO = new TextDTO();
//        textDTO.setSourceText("Спасибо");
//        final GenericResponseDTO<LanguageDTO> result = facade.detectLanguage(languageDTO);
//        final GenericResponseDTO<TextDTO> translationResult = facade.translate(textDTO);
//        System.out.println(result.getResponse().getDetectedLanguage());
//        System.out.println(translationResult.getResponse().getTargetTranslatedText());
//        facade.transliterate(textDTO);
//    }
//
    public void testGenderDetector(){
        GenderDetectorCreateRequestDTO genderDetectorCreateRequestDTO = new GenderDetectorCreateRequestDTO();
        genderDetectorCreateRequestDTO.setName("Գառնիկ");
        genderDetectorCreateRequestDTO.setGender(Gender.MALE);
        genderDetectorCreateRequestDTO.setLanguageCode(LanguageCode.hy);
        genderDetectorCreateRequestDTO.setCountry(Country.ARMENIA);
        genderDetectorCreateRequestDTO.setMachineLearned(false);
        genderDetectorCreateRequestDTO.setNameType(NameType.GIVEN_NAME);
        genderDetectorCreateRequestDTO.setTranslationEnglish("Garnique");
        genderDetectorCreateRequestDTO.setTransliterationEnglish("Garnik");
        genderDetectorCreateRequestDTO.setVerificationSource(NLPVerificationSource.LOCAL);
    }

    public void testThirdPartyService(){
        final ThirdPartySourceCreationRequest request = new ThirdPartySourceCreationRequest();
        request.setName(NLPThirdParty.GOOGLE);
        request.setUrl("google.com");
        request.setDescription("description");
        request.setServiceTypes(Sets.newHashSet(NLPServiceType.LANGUAGE_DETECTION, NLPServiceType.TRANSLATION));
        request.setCrawllingType(CrawllingType.API);
        ThirdPartySourceResponse response = sourceService.create(request);
        response = sourceService.get(response.getId());
    }
}
