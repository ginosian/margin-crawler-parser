package com.margin.endpoint;

import com.margin.dto.GenericListResponseDTO;
import com.margin.dto.GenericResponseDTO;
import com.margin.dto.scraper.SourceMetaDataDTO;
import com.margin.dto.scraper.source.ScraperSourceCreationDTO;
import com.margin.dto.scraper.source.ScraperSourceDTO;
import com.margin.dto.scraper.source.ScraperSourceExtendedDTO;
import com.margin.dto.scraper.source.ScraperSourceUpdateDTO;
import com.margin.enums.Channel;
import com.margin.enums.Country;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/source")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ScraperSourceEndpoint {

    @GET
    @Path("/{id}")
    GenericResponseDTO<ScraperSourceDTO> get(@PathParam("id") @NotNull Long id);

    @GET
    @Path("/{country}/{channel}")
    GenericResponseDTO<ScraperSourceDTO> find(@PathParam("country") @NotNull Country country, @PathParam("channel") @NotNull Channel channel);

    @GET
    @Path("/all")
    GenericListResponseDTO<ScraperSourceDTO> findAll();

    @GET
    @Path("/all/detailed")
    GenericListResponseDTO<ScraperSourceExtendedDTO> findAllDetailed();

    @GET
    @Path("/meta")
    GenericListResponseDTO<SourceMetaDataDTO> findAllMetaData();

    @GET
    @Path("/country/{country}")
    GenericListResponseDTO<ScraperSourceDTO> findAllByCountry(@PathParam("country") @NotNull Country country);

    @PUT
    @Path("/{country}/{channel}")
    GenericResponseDTO<ScraperSourceDTO> load(@PathParam("country") @NotNull Country country, @PathParam("channel") @NotNull Channel channel);

    @PUT
    @Path("/{id}")
    GenericResponseDTO<ScraperSourceDTO> load(@PathParam("id") @NotNull Long id);

    @PUT
    @Path("")
    GenericResponseDTO<ScraperSourceDTO> create(@NotNull ScraperSourceCreationDTO scraperSourceCreationDTO);

    @POST
    @Path("")
    GenericResponseDTO<ScraperSourceDTO> update(@NotNull ScraperSourceUpdateDTO scraperSourceCreationDTO);
}
