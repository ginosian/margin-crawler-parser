package com.margin.endpoint.impl;

import com.margin.error.ApiException;
import com.margin.dto.GenericResponseDTO;
import com.margin.enums.LoadingStage;
import com.margin.endpoint.OldStyleDBLoaderEndpoint;
import com.margin.loader.service.OldStyleDBLoaderService;
import com.margin.model.AbstractModel;
import com.margin.dto.tmp.loader.LoaderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class OldStyleDBLoaderEndpointImpl implements OldStyleDBLoaderEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(OldStyleDBLoaderEndpointImpl.class);

    @Autowired
    private OldStyleDBLoaderService loaderService;

    @Override
    public GenericResponseDTO<LoaderDTO> startLoadingSource(String databaseName) {
        GenericResponseDTO<LoaderDTO> response = null;
        try{
            response = new GenericResponseDTO<>(null, loaderService.startLoadingSource(databaseName));
        } catch (Exception e){
            if(e instanceof ApiException){
                response = new GenericResponseDTO<>((ApiException)e, null);
            } else {
                response = new GenericResponseDTO<>(create(e.getMessage(), LoadingStage.STARTING_DATABASE), null);
            }
        }
        return response;
    }

    @Override
    public GenericResponseDTO<LoaderDTO> loadNode(AbstractModel abstractModel) {
        GenericResponseDTO<LoaderDTO> response = null;
        try{
            response = new GenericResponseDTO<>(null, loaderService.loadNode(abstractModel));
        } catch (Exception e){
            if(e instanceof ApiException){
                response = new GenericResponseDTO<>((ApiException)e, null);
            } else {
                response = new GenericResponseDTO<>(create(e.getMessage(), LoadingStage.STARTING_DATABASE), null);
            }
        }
        return response;
    }

    @Override
    public GenericResponseDTO<LoaderDTO> stopLoadingSource(String databaseName) {
        GenericResponseDTO<LoaderDTO> response = null;
        try{
            response = new GenericResponseDTO<>(null, loaderService.finishLoadingSource(databaseName));
        } catch (Exception e){
            if(e instanceof ApiException){
                response = new GenericResponseDTO<>((ApiException)e, null);
            } else {
                response = new GenericResponseDTO<>(create(e.getMessage(), LoadingStage.STARTING_DATABASE), null);
            }
        }
        return response;
    }

    private ApiException create(final String message, final LoadingStage loadingStage){
        final ApiException exception = new ApiException(message);
        exception.setStage(loadingStage);
        return exception;
    }
}
