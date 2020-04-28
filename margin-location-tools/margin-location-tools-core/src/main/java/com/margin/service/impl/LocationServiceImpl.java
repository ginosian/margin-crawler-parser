package com.margin.service.impl;
/*
 *   @author ironman
 *   @since  11/1/18
 */

import com.margin.enums.Country;
import com.margin.entity.CountryEntity;
import com.margin.entity.Region;
import com.margin.repository.CountryRepository;
import com.margin.repository.RegionRepository;
import com.margin.service.LocationService;
import com.margin.service.MessageTools;
import com.margin.service.ValidationTools;
import com.margin.service.component.LocationConverter;
import com.margin.service.component.LocationValidator;
import com.margin.service.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.util.Assert.*;

@Service
public class LocationServiceImpl implements LocationService { // todo validation
    private static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private LocationConverter converter;

    @Autowired
    private LocationValidator validator;

    @Override
    public CountryResponse getCountry(Country country) {
        return converter.convert(getCountryEntity(country));
    }

    private CountryEntity getCountryEntity(Country country) {
        notNull(country, MessageTools.notNullMsg("country"));
        final CountryEntity countryEntity = countryRepository.getByName(country.name());
        ValidationTools.entityExists(countryEntity, "country", country.name());
        return countryEntity;
    }

    public CountryResponse createCountry(CountryCreationRequest request) {
        notNull(request.getName(), MessageTools.notNullMsg("name" ));
        notNull(request.getIsoCode(), MessageTools.notNullMsg("isoCode"));
        //todo validate
        return converter.convert(
                countryRepository.save(
                        converter.convert(request)));
    }

    @Override
    public CountryResponse updateCountry(CountryUpdateRequest update) {
        notNull(update.getName(), MessageTools.notNullMsg("country name"));
        //todo validate
        final CountryEntity countryEntity = getCountryEntity(update.getName());
        return converter.convert(
                countryRepository.save(countryEntity));
    }


    @Override
    public RegionResponse getRegion(Long id) {
        return converter.convert(getRegionEntity(id));
    }

    private Region getRegionEntity(Long id) {
        notNull(id, MessageTools.notNullMsg("id"));
        final Region region = regionRepository.getOne(id);
        ValidationTools.entityExists(region, "region", id);
        return region;
    }

    @Override
    public RegionResponse createRegion(RegionCreationRequest request) {
        notNull(request.getCountry(), MessageTools.notNullMsg("country"));
        hasText(request.getName(), MessageTools.notNullMsg("region name"));
        // todo validation
        return converter.convert(
                regionRepository.save(
                        converter.convert(request)));
    }

    @Override
    public RegionResponse updateRegion(RegionUpdateRequest update) {
        notNull(update.getId(), MessageTools.notNullMsg("id"));
        final Region regionEntity = getRegionEntity(update.getId());
        return converter.convert(
                regionRepository.save(regionEntity));
    }

    @Override
    public RegionResponse deleteRegion(Long id) {
        notNull(id, MessageTools.notNullMsg("id"));
        regionRepository.deleteById(id);
        return new RegionResponse();
    }

    @Override
    public Set<RegionResponse> searchRegion(RegionSearchRequest search) {
        hasText(search.getName(), MessageTools.notNullMsg("name"));
        final List<Region> regions = regionRepository.findAllByName(search.getName());
        return regions.stream().map(converter::convert).collect(Collectors.toSet());
    }

}
