package com.margin.endpoint.impl;

import com.margin.dto.GenericListResponseDTO;
import com.margin.dto.GenericResponseDTO;
import com.margin.dto.scraper.SourceMetaDataDTO;
import com.margin.dto.scraper.source.ScraperSourceCreationDTO;
import com.margin.dto.scraper.source.ScraperSourceDTO;
import com.margin.dto.scraper.source.ScraperSourceExtendedDTO;
import com.margin.dto.scraper.source.ScraperSourceUpdateDTO;
import com.margin.endpoint.EndpointHelper;
import com.margin.endpoint.ScraperSourceEndpoint;
import com.margin.enums.Channel;
import com.margin.enums.Country;
import com.margin.enums.LoadingStage;
import com.margin.loading.LoadingService;
import com.margin.mapper.BeanMapper;
import com.margin.service.SourceService;
import com.margin.service.model.SourceMetaDataModel;
import com.margin.service.model.source_info.SourceExtendedInfoResponce;
import com.margin.service.model.source_info.SourceInfoCreationRequest;
import com.margin.service.model.source_info.SourceInfoResponse;
import com.margin.service.model.source_info.SourceInfoUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.List;


public class ScraperSourceEndpointImpl implements ScraperSourceEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(ScraperSourceEndpointImpl.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SourceService sourceService;

    @Autowired
    private LoadingService loadingService;

    @Autowired
    private EndpointHelper endpointHelper;

    @Override
    public GenericResponseDTO<ScraperSourceDTO> get(@NotNull final Long id) {
        logger.debug("Getting ScraperSourceDTO with id: '{}'...", id);
        GenericResponseDTO<ScraperSourceDTO> response;
        try {
            final SourceInfoResponse sourceInfoResponse = sourceService.get(id);
            response = new GenericResponseDTO<>(null, beanMapper.map(sourceInfoResponse, ScraperSourceDTO.class));
        } catch (Exception e) {
            response = new GenericResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCRAPING), null);
        }
        return response;
    }

    @Override
    public GenericResponseDTO<ScraperSourceDTO> find(@NotNull final Country country, @NotNull final Channel channel) {
        GenericResponseDTO<ScraperSourceDTO> response;
        try {
            final List<SourceInfoResponse> sourceInfoResponseList = sourceService.search(new SourceMetaDataModel(null, country, channel));
            if (sourceInfoResponseList.isEmpty()) {
                response = new GenericResponseDTO<>();
            } else {
                final ScraperSourceDTO scraperSourceDTOS = beanMapper.map(sourceInfoResponseList.iterator().next(), ScraperSourceDTO.class);
                response = new GenericResponseDTO<>(null, scraperSourceDTOS);
            }
        } catch (Exception e) {
            response = new GenericResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCRAPING), null);
        }
        return response;
    }

    @Override
    public GenericListResponseDTO<ScraperSourceDTO> findAll() {
        GenericListResponseDTO<ScraperSourceDTO> response;
        try {
            final List<SourceInfoResponse> sourceInfoResponseList = sourceService.findAll();
            if (sourceInfoResponseList.isEmpty()) {
                response = new GenericListResponseDTO<>();
            } else {
                final List<ScraperSourceDTO> scraperSourceDTOS = beanMapper.mapAsList(sourceInfoResponseList, ScraperSourceDTO.class);
                response = new GenericListResponseDTO<>(null, scraperSourceDTOS.size(), scraperSourceDTOS);
            }
        } catch (Exception e) {
            response = new GenericListResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCRAPING), 0, null);
        }
        return response;
    }

    @Override
    public GenericListResponseDTO<ScraperSourceExtendedDTO> findAllDetailed() {
        GenericListResponseDTO<ScraperSourceExtendedDTO> response;
        try {
            final List<SourceExtendedInfoResponce> sourceInfoResponseList = sourceService.findAllWithDetails();
            if (sourceInfoResponseList.isEmpty()) {
                response = new GenericListResponseDTO<>();
            } else {
                final List<ScraperSourceExtendedDTO> scraperSourceDTOS = beanMapper.mapAsList(sourceInfoResponseList, ScraperSourceExtendedDTO.class);
                response = new GenericListResponseDTO<>(null, scraperSourceDTOS.size(), scraperSourceDTOS);
            }
        } catch (Exception e) {
            response = new GenericListResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCRAPING), 0, null);
        }
        return response;
    }

    @Override
    public GenericListResponseDTO<SourceMetaDataDTO> findAllMetaData() {
        GenericListResponseDTO<SourceMetaDataDTO> response;
        try {
            final List<SourceMetaDataModel> scrapedSourceResponseList = sourceService.findAllSourceMetaData();
            if (scrapedSourceResponseList.isEmpty()) {
                response = new GenericListResponseDTO<>();
            } else {
                final List<SourceMetaDataDTO> scraperSourceDTOS = beanMapper.mapAsList(scrapedSourceResponseList, SourceMetaDataDTO.class);
                response = new GenericListResponseDTO<>(null, scraperSourceDTOS.size(), scraperSourceDTOS);
            }
        } catch (Exception e) {
            response = new GenericListResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCRAPING), 0, null);
        }
        return response;
    }

    @Override
    public GenericListResponseDTO<ScraperSourceDTO> findAllByCountry(@NotNull final Country country) {
        GenericListResponseDTO<ScraperSourceDTO> response;
        try {
            final List<SourceInfoResponse> sourceInfoResponseList = sourceService.search(new SourceMetaDataModel(null, country, null));
            if (sourceInfoResponseList.isEmpty()) {
                response = new GenericListResponseDTO<>();
            } else {
                final List<ScraperSourceDTO> scraperSourceDTOS = beanMapper.mapAsList(sourceInfoResponseList, ScraperSourceDTO.class);
                response = new GenericListResponseDTO<>(null, scraperSourceDTOS.size(), scraperSourceDTOS);
            }
        } catch (Exception e) {
            response = new GenericListResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCRAPING), 0, null);
        }
        return response;
    }

    @Override
    public GenericResponseDTO<ScraperSourceDTO> load(@NotNull final Country country, @NotNull final Channel channel) {
        GenericResponseDTO<ScraperSourceDTO> response;
        try {
            final List<SourceInfoResponse> sourceInfoResponseList = sourceService.search(new SourceMetaDataModel(null, country, channel));
            if (sourceInfoResponseList.isEmpty()) {
                response = new GenericResponseDTO<>(endpointHelper.create("No scraping source created, could not load.", LoadingStage.SCRAPING), null);
            } else {
                final SourceInfoResponse scraperSourceResponse = sourceInfoResponseList.iterator().next();
                loadingService.load(scraperSourceResponse.getId());
                response = new GenericResponseDTO<>(null, beanMapper.map(sourceService.get(scraperSourceResponse.getId()), ScraperSourceDTO.class));
            }
        } catch (Exception e) {
            response = new GenericResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCRAPING), null);
        }
        return response;
    }

    @Override
    public GenericResponseDTO<ScraperSourceDTO> load(@NotNull final Long id) {
        GenericResponseDTO<ScraperSourceDTO> response;
        try {
            final SourceInfoResponse sourceInfoResponseList = sourceService.get(id);
            loadingService.load(sourceInfoResponseList.getId());
            response = new GenericResponseDTO<>(null, beanMapper.map(sourceService.get(id), ScraperSourceDTO.class));
        } catch (Exception e) {
            response = new GenericResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCRAPING), null);
        }
        return response;
    }


    @Override
    public GenericResponseDTO<ScraperSourceDTO> create(@NotNull final ScraperSourceCreationDTO scraperSourceCreationDTO) {
        GenericResponseDTO<ScraperSourceDTO> response;
        try {
            final SourceInfoCreationRequest sourceInfoCreationRequest = beanMapper.map(scraperSourceCreationDTO, SourceInfoCreationRequest.class);
            final SourceInfoResponse sourceInfoResponse = sourceService.create(sourceInfoCreationRequest);
            final ScraperSourceDTO scraperSourceDTO = beanMapper.map(sourceInfoResponse, ScraperSourceDTO.class);
            response = new GenericResponseDTO<>(null, scraperSourceDTO);
        } catch (Exception e) {
            response = new GenericResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCRAPING), null);
        }
        return response;
    }


    @Override
    public GenericResponseDTO<ScraperSourceDTO> update(@NotNull final ScraperSourceUpdateDTO scraperSourceCreationDTO) {
        GenericResponseDTO<ScraperSourceDTO> response;
        try {
            final SourceInfoUpdateRequest scrapedSourceCreationRequest
                    = beanMapper.map(scraperSourceCreationDTO, SourceInfoUpdateRequest.class);
            final SourceInfoResponse sourceInfoResponse = sourceService.update(scrapedSourceCreationRequest);
            final ScraperSourceDTO scraperSourceDTO = beanMapper.map(sourceInfoResponse, ScraperSourceDTO.class);
            response = new GenericResponseDTO<>(null, scraperSourceDTO);
        } catch (Exception e) {
            response = new GenericResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCRAPING), null);
        }
        return response;
    }
}
