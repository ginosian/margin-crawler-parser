package com.margin.service.component;

import com.margin.enums.RegionType;
import com.margin.entity.CountryEntity;
import com.margin.entity.Region;
import com.margin.mapper.BeanMapper;
import com.margin.service.model.CountryCreationRequest;
import com.margin.service.model.CountryResponse;
import com.margin.service.model.RegionCreationRequest;
import com.margin.service.model.RegionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationConverter {
    private static final Logger logger = LoggerFactory.getLogger(LocationConverter.class);

    @Autowired
    private BeanMapper mapper;

    public CountryEntity convert(final CountryCreationRequest request) {
        return mapper.map(request, CountryEntity.class);
    }

    public CountryResponse convert(final CountryEntity countryEntity) {
        return mapper.map(countryEntity, CountryResponse.class);
    }

    public Region convert(final RegionCreationRequest request) {
        final Region region = new Region();
        region.setName(request.getName());
        region.setType(request.getType());
        return region;
    }

    public RegionResponse convert(final Region region) {
        final RegionResponse response = new RegionResponse();
        response.setCountry(region.getCountryEntity().getName());
        response.setName(region.getName());
        response.setType(region.getType());
        return response;
    }

    private Region createRegion(String name, RegionType type) {
        final Region region = new Region();
        region.setName(name);
        region.setType(type);
        return region;
    }
}
