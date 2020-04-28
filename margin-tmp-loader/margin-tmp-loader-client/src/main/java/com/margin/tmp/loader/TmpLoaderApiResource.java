package com.margin.tmp.loader;

import com.margin.AbstractApiResource;
import com.margin.dto.GenericResponseDTO;
import com.margin.dto.tmp.loader.LoaderDTO;
import com.margin.model.AbstractModel;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class TmpLoaderApiResource extends AbstractApiResource {

    private static String PATH_SUFFIX = "/load";

    public TmpLoaderApiResource(Client client, WebTarget rootTarget) {
        super(client, rootTarget, PATH_SUFFIX);
    }

    public GenericResponseDTO<LoaderDTO> loadNode(final AbstractModel document) {
        return doPut("", document, new GenericType<GenericResponseDTO<LoaderDTO>>(){});
    }

    public GenericResponseDTO<LoaderDTO> startLoadingSource(final String databaseName) {
        return doGet("/start/" + databaseName, new GenericType<GenericResponseDTO<LoaderDTO>>(){});
    }

    public GenericResponseDTO<LoaderDTO> stopLoadingSource(final String databaseName) {
        return doGet("/stop/" + databaseName, new GenericType<GenericResponseDTO<LoaderDTO>>(){});
    }
}
