package com.margin.service.component;

import com.margin.service.model.CountryCreationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LocationValidator {
    private static final Logger logger = LoggerFactory.getLogger(LocationValidator.class);

    public void validate(CountryCreationRequest entity) {
        onlyLatinLetters(length(entity.getIsoCode(), 2));
        onlyLatinLetters(length(entity.getUnCode(), 3));
        onlyLatinLetters(length(entity.getCurrencyCode(), 3));
    }

    private String length(String text, int length) {
        if(text.length() != length) throw new IllegalStateException("text length should be " + length);
        return text;
    }

    private String onlyLettersAndNumbers(String text, int length) {
        if (text.matches("\\d+")) throw new IllegalArgumentException("text should ");
        return text;
    }

    private String onlyLatinLetters(String text) {
        if(!text.matches("\\w+")) throw new IllegalArgumentException("text contain only latin letters");
        return text;
    }


    private double inRange(double number, double min, double max) {
        if(number < min || number > max) throw new IllegalArgumentException(String.format("number should be in range"));
        return number;
    }

}
