package com.margin.endpoint;

import com.margin.dto.info.InfoDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/info")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface EntityToolsInfoEndpoint {

    @GET
    InfoDTO get();

    @POST
    InfoDTO post(InfoDTO infoDTO);
}
