package com.margin.endpoint;

import com.margin.dto.GenericResponseDTO;
import com.margin.model.AbstractModel;
import com.margin.dto.tmp.loader.LoaderDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/load")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface OldStyleDBLoaderEndpoint {

    @GET
    @Path("/start/{databaseName}")
    GenericResponseDTO<LoaderDTO> startLoadingSource(@PathParam("databaseName") String databaseName);

    @PUT
    @Path(value = "")
    GenericResponseDTO<LoaderDTO> loadNode(AbstractModel abstractModel);

    @GET
    @Path("/stop/{databaseName}")
    GenericResponseDTO<LoaderDTO> stopLoadingSource(@PathParam("databaseName") String databaseName);

}
