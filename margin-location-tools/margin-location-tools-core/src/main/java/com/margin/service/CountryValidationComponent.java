package com.margin.service;

import com.margin.service.model.CountryCreationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CountryValidationComponent {
    private static final Logger logger = LoggerFactory.getLogger(CountryValidationComponent.class);

    public void validate(final CountryCreationRequest countryCreationRequest){

    }

}
