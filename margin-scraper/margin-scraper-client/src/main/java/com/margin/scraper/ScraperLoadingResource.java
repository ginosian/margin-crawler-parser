package com.margin.scraper;

import com.margin.AbstractApiResource;
import com.margin.dto.GenericResponseDTO;
import com.margin.dto.tmp.loader.LoaderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class ScraperLoadingResource extends AbstractApiResource {

    private static String PATH_SUFFIX = "/load";

    private final Logger logger = LoggerFactory.getLogger(ScraperLoadingResource.class);

    public ScraperLoadingResource(Client client, WebTarget rootTarget) {
        super(client, rootTarget, PATH_SUFFIX);
    }

    public GenericResponseDTO<LoaderDTO> load(final Long id) {
        return doGet("/" + id, new GenericType<GenericResponseDTO<LoaderDTO>>() {
        });
    }

}
