package com.margin.service.genderapi;

import com.margin.enums.Country;
import com.margin.enums.EntityType;
import com.margin.enums.Gender;
import com.margin.enums.NameType;
import com.margin.model.NLPDataResponse;
import com.margin.model.NLPRequest;
import com.margin.service.GenderThirdPartyService;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.notNull;

@Service
public class GenderizeServiceImpl extends AbstractGenderService implements GenderThirdPartyService {
    @Override
    public NLPDataResponse detectGender(NLPRequest request) {
        notNull(request, "Request for gender detection can not be null.");
        final String name = request.getSourceText();
        notNull(name, "Data for gender detection can not be null.");

        final Country sourceCountry = request.getSourceCountry();
        final String urlString = sourceCountry == null ?
                String.format("{url}", name) :
                String.format("{url}" + "{localeParameter}", name, sourceCountry);
        final String gender = requestTheUrl(urlString);

        NLPDataResponse response = new NLPDataResponse();
        response.setSourceText(name);
        response.setNameType(NameType.GIVEN_NAME);
        response.setGender(Gender.valueOf(gender));
        response.setEntityType(EntityType.PERSON);
        return response;
    }
}
