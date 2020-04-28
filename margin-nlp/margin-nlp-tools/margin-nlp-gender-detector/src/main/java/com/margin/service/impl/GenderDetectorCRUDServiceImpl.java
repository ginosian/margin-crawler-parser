package com.margin.service.impl;

import com.margin.entity.NLPPerson;
import com.margin.enums.*;
import com.margin.model.NLPDataResponse;
import com.margin.model.NLPResponse;
import com.margin.model.genderdetector.GenderDetectorCreateRequest;
import com.margin.model.genderdetector.GenderDetectorCreateResponse;
import com.margin.repository.NLPPersonRepository;
import com.margin.service.GenderDetectorCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.notNull;

@Service
public class GenderDetectorCRUDServiceImpl implements GenderDetectorCRUDService {

    @Autowired
    private NLPPersonRepository nlpPersonRepository;

    @Override
    public void save() {
        final NLPPerson person = new NLPPerson();
        person.setName("Marta");
        nlpPersonRepository.save(person);
    }


    @Override
    public GenderDetectorCreateResponse create(GenderDetectorCreateRequest request) {
        notNull(request, "Request cannot be null.");
        String name = request.getName();
        Gender gender = request.getGender();
        LanguageCode languageCode = request.getLanguageCode();
        NameType nameType = request.getNameType();
        Country country = request.getCountry();
        NLPVerificationSource verificationSource = request.getVerificationSource();
        String transliterationEnglish = request.getTransliterationEnglish();
        String translationEnglish = request.getTranslationEnglish();
        Boolean machineLearned = request.getMachineLearned();

        notNull(name, "name cannot be null.");
        notNull(gender, "gender cannot be null.");
        notNull(languageCode, "languageCode cannot be null.");
        notNull(nameType, "nameType cannot be null.");
        notNull(country, "country cannot be null.");
        notNull(verificationSource, "verificationSource cannot be null.");
        notNull(transliterationEnglish, "transliterationEnglish cannot be null.");
        notNull(translationEnglish, "translationEnglish cannot be null.");
        notNull(machineLearned, "machineLearned cannot be null.");


        NLPPerson person = new NLPPerson();
        person.setNameType(request.getNameType());
        person.setName(request.getName());
        person.setGender(request.getGender());
        person.setCountry(request.getCountry());
        person.setLanguage(request.getLanguageCode());
        person.setEntityType(EntityType.PERSON);
        person.setMachineLearned(request.getMachineLearned());
        person.setTranslationEnglish(request.getTranslationEnglish());
        person.setVerificationSource(request.getVerificationSource());
        person.setTransliterationEnglish(request.getTransliterationEnglish());
        NLPPerson p = nlpPersonRepository.save(person);

        GenderDetectorCreateResponse response = new GenderDetectorCreateResponse();
        response.set_id(p.get_id());
        return response;
    }
//
//    @Override
//    public NameGenderResponse update(NameGenderEntityUpdateRequest request) {
//        return null;
//    }
//
//    @Override
//    public NameGenderResponse get(Long id) {
//        return null;
//    }
//
//    @Override
//    public List<NameGenderResponse> search(NameGenderEntitySearchRequest request) {
//        notNull(request, "Request cannot be null.");
//        String name = request.getName();
//        String language = request.getLanguage();
//        notNull(name, "name cannot be null.");
//        List<NameGenderEntity> queryResult = new ArrayList<>();
//        if (!request.getName().isEmpty()) {
//            if (!request.getLanguage().isEmpty()) {
//                queryResult = genderDetectorRepository.findAllByNameAndLanguage(name, language);
//            } else {
//                queryResult = genderDetectorRepository.findAllByName(name);
//            }
//        }
//
//        List<NameGenderResponse> response = queryResult.stream().map(entity -> {
//            return mapper.map(entity, NameGenderResponse.class);
//        }).collect(Collectors.toList());
//
//        return response;
//    }
}
