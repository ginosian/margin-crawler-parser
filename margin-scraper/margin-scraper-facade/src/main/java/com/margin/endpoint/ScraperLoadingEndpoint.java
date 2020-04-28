package com.margin.endpoint;


import com.margin.dto.GenericResponseDTO;
import com.margin.dto.tmp.loader.LoaderDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/load")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ScraperLoadingEndpoint {

    @GET
    @Path("/{scrapingSourceInfoId}")
    GenericResponseDTO<LoaderDTO> get(@PathParam("scrapingSourceInfoId") final Long scrapingSourceInfoId);

}
