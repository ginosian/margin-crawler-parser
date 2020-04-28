package com.margin.scheduler.job.api;

import com.margin.AbstractApiResource;
import com.margin.dto.GenericListResponseDTO;
import com.margin.dto.GenericResponseDTO;
import com.margin.dto.scheduler.SchedulerJobDTO;
import com.margin.dto.scheduler.SchedulerMetaDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class SchedulerResource extends AbstractApiResource {

    private static String PATH_SUFFIX = "/jobs";

    private final Logger logger = LoggerFactory.getLogger(SchedulerResource.class);


    public SchedulerResource(Client client, WebTarget rootTarget) {
        super(client, rootTarget, PATH_SUFFIX);
    }

    public GenericListResponseDTO<SchedulerJobDTO> getAll() {
        return doGet("/getAllJobs", new GenericType<GenericListResponseDTO<SchedulerJobDTO>>() {
        });
    }

    public GenericResponseDTO<SchedulerMetaDataDTO> metaData(){
        return doGet("/metaData", new GenericType<GenericResponseDTO<SchedulerMetaDataDTO>>(){});
    }

    public GenericListResponseDTO<SchedulerJobDTO> getRunningJobs(){
        return doGet("/getRunningJobs", new GenericType<GenericListResponseDTO<SchedulerJobDTO>>() {});
    }

    public GenericResponseDTO<String> pauseJob(SchedulerJobDTO job){
        return doPost("/pause", job, new GenericType<GenericResponseDTO<String>>() {});
    }

    public GenericResponseDTO<String> resumeJob(SchedulerJobDTO job){
        return doPost("/resume", job, new GenericType<GenericResponseDTO<String>>() {});
    }


    public GenericResponseDTO<String> deleteJob(SchedulerJobDTO job){
        return doPost("/delete", job, new GenericType<GenericResponseDTO<String>>() {});
    }

    public GenericResponseDTO<String> runJob(SchedulerJobDTO job){
        return doPost("/run", job, new GenericType<GenericResponseDTO<String>>() {});
    }


    public GenericResponseDTO<String> saveOrUpdate(SchedulerJobDTO job){
        return doPost("/save", job, new GenericType<GenericResponseDTO<String>>() {});
    }

}
