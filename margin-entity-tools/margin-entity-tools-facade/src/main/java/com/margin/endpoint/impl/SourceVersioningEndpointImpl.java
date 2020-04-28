package com.margin.endpoint.impl;

import com.margin.dto.GenericResponseDTO;
import com.margin.dto.tmp.loader.LoaderDTO;
import com.margin.endpoint.SourceVersioningEndpoint;
import com.margin.enums.LoadingStage;
import com.margin.error.ApiException;
import com.margin.model.AbstractModel;
import com.margin.service.impl.AbstractSourceVersioningServiceImpl;
import com.margin.tmp.loader.api.TmpLoaderApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

public class SourceVersioningEndpointImpl implements SourceVersioningEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(SourceVersioningEndpointImpl.class);

    @Autowired
    private AbstractSourceVersioningServiceImpl sourceVersioningService;

    @Autowired
    private TmpLoaderApiClient tmpLoaderApiClient;

    @Override
    public GenericResponseDTO<LoaderDTO> start(@NotNull String database) {
        GenericResponseDTO<LoaderDTO> response = null;
        logger.debug("Starting source versioning of database: '{}'...", database);
        try {
            final GenericResponseDTO responseFromStart = tmpLoaderApiClient.tmpLoader().startLoadingSource(database);
            if (responseFromStart.getException() != null) {
                response = new GenericResponseDTO<>(responseFromStart.getException(), null);
                logger.debug("Exception has occurred while loading source of database: '{}'. Message: {}.",
                        database, responseFromStart.getException().getMessage());
            } else {
                response = new GenericResponseDTO<>(null, new LoaderDTO());
                logger.info("Done starting source versioning of database: '{}'.", database);
            }
        } catch (Exception e) {
            final ApiException apiException = new ApiException(e.getMessage());
            apiException.setStage(LoadingStage.STARTING_DATABASE);
            response = new GenericResponseDTO<>(apiException, null);
            logger.debug("Exception has occurred while starting source versioning of database: '{}'. Message: {}.",
                    database, apiException.getMessage());
        }
        return response;
    }

    @Override
    public GenericResponseDTO<LoaderDTO> load(@NotNull AbstractModel model) {
        GenericResponseDTO<LoaderDTO> response = null;
        logger.debug("Loading source versioning of AbstractModel...");
        try {
            final AbstractModel modelFromDb = sourceVersioningService.create(model);
            response = tmpLoaderApiClient.tmpLoader().loadNode(modelFromDb);
            logger.info("Done loading source versioning of AbstractModel:'{}'.", modelFromDb.get_id());
        } catch (Exception e) {
            final ApiException apiException = new ApiException(e.getMessage());
            apiException.setStage(LoadingStage.SOURCE_VERSIONING);
            response = new GenericResponseDTO<>(apiException, null);
            logger.debug("Exception has occurred while loading source versioning of AbstractModel. Message: {}.",
                    model, apiException.getMessage());
        }
        return response;
    }

    @Override
    public GenericResponseDTO<LoaderDTO> complete(@NotNull String database) {
        GenericResponseDTO<LoaderDTO> response = null;
        logger.debug("Completing source versioning of database: '{}'...", database);
        try {
            final GenericResponseDTO responseFromStart = tmpLoaderApiClient.tmpLoader().stopLoadingSource(database);
            if (responseFromStart.getException() != null) {
                response = new GenericResponseDTO<>(responseFromStart.getException(), null);
                logger.debug("Exception has occurred while stopping loading source of database: '{}'. Message: {}.",
                        database, responseFromStart.getException().getMessage());
            } else {
                response = new GenericResponseDTO<>(null, new LoaderDTO());
                logger.info("Done completing source versioning of database: '{}'.", database);
            }
        } catch (Exception e) {
            final ApiException apiException = new ApiException(e.getMessage());
            apiException.setStage(LoadingStage.COMPLETING_DATABASE);
            response = new GenericResponseDTO<>(apiException, null);
            logger.debug("Exception has occurred while completing source versioning of database: '{}'. Message: {}.",
                    database, apiException.getMessage());
        }
        return response;
    }
}
