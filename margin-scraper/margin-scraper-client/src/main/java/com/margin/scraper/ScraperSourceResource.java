package com.margin.scraper;

import com.margin.AbstractApiResource;
import com.margin.dto.GenericListResponseDTO;
import com.margin.dto.GenericResponseDTO;
import com.margin.dto.scraper.SourceMetaDataDTO;
import com.margin.dto.scraper.source.ScraperSourceCreationDTO;
import com.margin.dto.scraper.source.ScraperSourceDTO;
import com.margin.dto.scraper.source.ScraperSourceExtendedDTO;
import com.margin.dto.scraper.source.ScraperSourceUpdateDTO;
import com.margin.enums.Channel;
import com.margin.enums.Country;
import com.margin.model.AbstractModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class ScraperSourceResource extends AbstractApiResource {

    private static String PATH_SUFFIX = "/source";

    private final Logger logger = LoggerFactory.getLogger(ScraperSourceResource.class);

    public ScraperSourceResource(Client client, WebTarget rootTarget) {
        super(client, rootTarget, PATH_SUFFIX);
    }

    public GenericResponseDTO<ScraperSourceDTO> create(final AbstractModel document) {
        return doPut("", document, new GenericType<GenericResponseDTO<ScraperSourceDTO>>() {
        });
    }

    public GenericResponseDTO<ScraperSourceDTO> get(final Long id) {
        return doGet("/" + id, new GenericType<GenericResponseDTO<ScraperSourceDTO>>() {
        });
    }

    public GenericResponseDTO<ScraperSourceDTO> find(final Country country, final Channel channel) {
        return doGet("/" + country + "/" + channel, new GenericType<GenericResponseDTO<ScraperSourceDTO>>() {
        });
    }

    public GenericListResponseDTO<ScraperSourceDTO> findAll() {
        return doGet("/all", new GenericType<GenericListResponseDTO<ScraperSourceDTO>>() {
        });
    }

    public GenericListResponseDTO<ScraperSourceExtendedDTO> findAllDetailed() {
        return doGet("/all/detailed", new GenericType<GenericListResponseDTO<ScraperSourceExtendedDTO>>() {
        });
    }

    public GenericListResponseDTO<SourceMetaDataDTO> findAllMetaData() {
        return doGet("/meta", new GenericType<GenericListResponseDTO<SourceMetaDataDTO>>() {
        });
    }

    public GenericListResponseDTO<ScraperSourceDTO> findAllByCountry(final Country country) {
        return doGet("/country/" + country, new GenericType<GenericListResponseDTO<ScraperSourceDTO>>() {
        });
    }

    GenericResponseDTO<ScraperSourceDTO> load(final Country country, final Channel channel) {
        return doPut("/" + country + "/" + channel, null, new GenericType<GenericResponseDTO<ScraperSourceDTO>>() {
        });
    }

    GenericResponseDTO<ScraperSourceDTO> load(final Long id) {
        return doPut("/" + id, null, new GenericType<GenericResponseDTO<ScraperSourceDTO>>() {});
    }

    GenericResponseDTO<ScraperSourceDTO> create(final ScraperSourceCreationDTO scraperSourceCreationDTO) {
        return doPut("", scraperSourceCreationDTO, new GenericType<GenericResponseDTO<ScraperSourceDTO>>() {
        });
    }

    GenericResponseDTO<ScraperSourceDTO> update(final ScraperSourceUpdateDTO scraperSourceCreationDTO) {
        return doPost("", scraperSourceCreationDTO, new GenericType<GenericResponseDTO<ScraperSourceDTO>>() {
        });
    }
}
