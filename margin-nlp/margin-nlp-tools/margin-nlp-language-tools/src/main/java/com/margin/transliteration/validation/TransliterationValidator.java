package com.margin.transliteration.validation;

import com.margin.enums.LoadingStage;
import com.margin.error.ApiException;
import com.margin.model.NLPRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Component
public class TransliterationValidator {

    public void validateEN(NLPRequest request, String transliterated) {
        if (!isValidTransliteration(transliterated)) {
            String errorMessage = "This couldn't be transliterated \"" + request.getSourceText() + "\" from language ";
            if (request.getSourceLanguageCode() != null) {
                errorMessage = errorMessage + request.getSourceLanguageCode().name() + "to language " + request.getTargetLanguageCode().name();
            } else {
                errorMessage = errorMessage + String.join(",", request.getSourcePossibleLanguages().stream().map(Objects::toString).collect(toList())) +  "to language " + request.getTargetLanguageCode().name();
            }

            ApiException e = new ApiException(errorMessage);
            e.setStage(LoadingStage.NLP_TRANSLITERATION);
            throw e;
        }
    }

    private boolean isValidTransliteration(String text) {
        return text.matches("[^\\p{L}\\p{Mn}[\\w]]+");
    }
}
