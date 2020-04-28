package com.margin.endpoint;

import com.margin.dto.GenericResponseDTO;
import com.margin.dto.tmp.loader.LoaderDTO;
import com.margin.model.AbstractModel;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/source/versioning")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface SourceVersioningEndpoint {

    @GET
    @Path("/start/{database}")
    GenericResponseDTO<LoaderDTO> start(@NotNull @PathParam("database") final String database);

    @POST
    @Path("")
    GenericResponseDTO<LoaderDTO> load(@NotNull final AbstractModel model);


    @GET
    @Path("/complete/{database}")
    GenericResponseDTO<LoaderDTO> complete(@NotNull @PathParam("database") final String database);

}
