package com.margin.endpoint.impl;

import com.margin.dto.GenericResponseDTO;
import com.margin.dto.tmp.loader.LoaderDTO;
import com.margin.endpoint.EndpointHelper;
import com.margin.endpoint.ScraperLoadingEndpoint;
import com.margin.enums.LoadingStage;
import com.margin.loading.LoadingService;
import org.springframework.beans.factory.annotation.Autowired;

public class ScraperLoadingEndpointImpl implements ScraperLoadingEndpoint {

    @Autowired
    private LoadingService loadingService;

    @Autowired
    private EndpointHelper endpointHelper;

    @Override
    public GenericResponseDTO<LoaderDTO> get(Long scrapingSourceInfoId) {
        GenericResponseDTO<LoaderDTO> response;
        try {
            loadingService.load(scrapingSourceInfoId);
            response = new GenericResponseDTO<>(null, new LoaderDTO());
        } catch (Exception e){
            response = new GenericResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCRAPING), null);
        }
        return response;
    }
}
