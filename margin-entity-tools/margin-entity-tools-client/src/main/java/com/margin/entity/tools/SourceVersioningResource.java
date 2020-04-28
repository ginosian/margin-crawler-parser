package com.margin.entity.tools;

import com.margin.AbstractApiResource;
import com.margin.dto.GenericResponseDTO;
import com.margin.dto.tmp.loader.LoaderDTO;
import com.margin.model.AbstractModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class SourceVersioningResource extends AbstractApiResource {

    private static String PATH_SUFFIX = "/source/versioning";

    private final Logger logger = LoggerFactory.getLogger(SourceVersioningResource.class);

    public SourceVersioningResource(Client client, WebTarget rootTarget) {
        super(client, rootTarget, PATH_SUFFIX);
    }

    public GenericResponseDTO<LoaderDTO> load(final AbstractModel model) {
        return doPost("", model, new GenericType<GenericResponseDTO<LoaderDTO>>(){});
    }

    public GenericResponseDTO<LoaderDTO> startDatabase(final String database){
        return doGet("/start/" + database, new GenericType<GenericResponseDTO<LoaderDTO>>(){});
    }

    public GenericResponseDTO<LoaderDTO> completeDatabase(final String database){
        return doGet("/complete/" + database, new GenericType<GenericResponseDTO<LoaderDTO>>(){});
    }
}
