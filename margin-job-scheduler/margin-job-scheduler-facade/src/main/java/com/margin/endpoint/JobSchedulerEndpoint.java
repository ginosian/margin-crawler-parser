package com.margin.endpoint;

import com.margin.dto.GenericListResponseDTO;
import com.margin.dto.GenericResponseDTO;
import com.margin.dto.scheduler.SchedulerJobDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/jobs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface JobSchedulerEndpoint {

    @GET
    @Path("/getAllJobs")
    GenericListResponseDTO<SchedulerJobDTO> getAllJobs();

    @GET
    @Path("/getRunningJobs")
    GenericListResponseDTO<SchedulerJobDTO> getRunningJobs();

    @POST
    @Path(value = "/pause")
    GenericResponseDTO<String> pauseJob(SchedulerJobDTO job);

    @POST
    @Path(value = "/resume")
    GenericResponseDTO<String> resumeJob(SchedulerJobDTO job);

    @POST
    @Path(value = "/delete")
    GenericResponseDTO<String> deleteJob(SchedulerJobDTO job);

    @POST
    @Path(value = "/run")
    GenericResponseDTO<String> runJob(SchedulerJobDTO job);

    @POST
    @Path(value = "/save")
    GenericResponseDTO<String> saveOrUpdate(SchedulerJobDTO job);
}
