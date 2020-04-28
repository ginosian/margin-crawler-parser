package com.margin.service;
/*
 *   @author ironman
 *   @since  11/1/18
 */

import com.margin.enums.Country;
import com.margin.service.model.*;

import java.util.Set;

public interface LocationService {
    CountryResponse getCountry(Country country);

//    List<CountryResponse> getAllCountry();

    CountryResponse createCountry(CountryCreationRequest request);

    CountryResponse updateCountry(CountryUpdateRequest update);


    RegionResponse getRegion(Long id);

//    List<RegionResponse> getAllRegion();

    RegionResponse createRegion(RegionCreationRequest request);

    RegionResponse updateRegion(RegionUpdateRequest update);

    RegionResponse deleteRegion(Long id);

    Set<RegionResponse> searchRegion(RegionSearchRequest search);
}
