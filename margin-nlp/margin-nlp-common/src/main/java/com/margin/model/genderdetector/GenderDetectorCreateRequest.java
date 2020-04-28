package com.margin.model.genderdetector;

import com.margin.enums.Country;
import com.margin.enums.Gender;
import com.margin.enums.LanguageCode;
import com.margin.enums.NLPVerificationSource;
import com.margin.enums.NameType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenderDetectorCreateRequest {
    private Gender gender;
    private NameType nameType;
    private String name;
    private LanguageCode languageCode;
    private Country country;
    private NLPVerificationSource verificationSource;
    private String transliterationEnglish;
    private String translationEnglish;
    private Boolean machineLearned;
}
