package com.margin.endpoint.impl;

import com.margin.dto.GenericListResponseDTO;
import com.margin.dto.GenericResponseDTO;
import com.margin.dto.scheduler.SchedulerJobDTO;
import com.margin.endpoint.EndpointHelper;
import com.margin.endpoint.JobSchedulerEndpoint;
import com.margin.enums.LoadingStage;
import com.margin.job.ScheduleJob;
import com.margin.mapper.BeanMapper;
import com.margin.service.SourceVersioningScheduleJobServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class JobSchedulerEndpointImpl implements JobSchedulerEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(JobSchedulerEndpointImpl.class);

    @Autowired
    private SourceVersioningScheduleJobServiceImpl scheduleJobService;

    @Autowired
    private BeanMapper mapper;

    @Autowired
    private EndpointHelper endpointHelper;

    @Override
    public GenericListResponseDTO<SchedulerJobDTO> getAllJobs() {
        logger.debug("Getting all schedule jobs...");
        final List<ScheduleJob> jobList = scheduleJobService.getAll();
        final List<SchedulerJobDTO> jobDTOList = jobList.stream().map(job -> mapper.map(job, SchedulerJobDTO.class)).collect(Collectors.toList());
        logger.info("Done getting {} schedule jobs.", jobList.size());
        return new GenericListResponseDTO<>(null, jobDTOList.size(), jobDTOList);

    }

    @Override
    public GenericListResponseDTO<SchedulerJobDTO> getRunningJobs() {
        logger.debug("Getting all running schedule jobs...");
        try {
            final List<ScheduleJob> jobList = scheduleJobService.getAllRunning();
            final List<SchedulerJobDTO> jobDTOList = jobList.stream().map(job -> mapper.map(job, SchedulerJobDTO.class)).collect(Collectors.toList());
            logger.info("Done getting {} running schedule jobs.", jobList.size());
            return new GenericListResponseDTO<>(null, jobDTOList.size(), jobDTOList);
        } catch (Exception e) {
            logger.debug("Exception was occurred while retrieving all running schedule jobs: {}.", e.getMessage());
            return new GenericListResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCHEDULING), 0, null);
        }

    }

    @Override
    public GenericResponseDTO<String> pauseJob(SchedulerJobDTO job) {
        logger.debug("Pausing schedule job with id: '{}'...", job.getSourceInfoId());
        try {
            final ScheduleJob scheduleJob = mapper.map(job, ScheduleJob.class);
            scheduleJobService.pause(scheduleJob);
            logger.info("Done pausing schedule job with id: '{}'.", job.getSourceInfoId());
            return new GenericResponseDTO<>(null, "SUCCESS");
        } catch (Exception e) {
            logger.debug("Exception was occurred while pausing schedule job with id: '{}'. Message: {}",
                    job.getSourceInfoId(), e.getMessage());
            return new GenericResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCHEDULING), null);
        }
    }

    @Override
    public GenericResponseDTO<String> resumeJob(SchedulerJobDTO job) {
        logger.debug("Resuming schedule job with id: '{}'...", job.getSourceInfoId());
        try {
            final ScheduleJob scheduleJob = mapper.map(job, ScheduleJob.class);
            scheduleJobService.resume(scheduleJob);
            logger.info("Done resuming schedule job with id: '{}'.", scheduleJob.getSourceInfoId());
            return new GenericResponseDTO<>(null, "SUCCESS");
        } catch (Exception e) {
            logger.debug("Exception was occurred while resuming schedule job with id: '{}'. Message: {}",
                    job.getSourceInfoId(), e.getMessage());
            return new GenericResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCHEDULING), null);
        }
    }

    @Override
    public GenericResponseDTO<String> deleteJob(SchedulerJobDTO job) {
        logger.debug("Deleting schedule job with id: '{}'...", job.getSourceInfoId());
        try {
            final ScheduleJob scheduleJob = mapper.map(job, ScheduleJob.class);
            scheduleJobService.delete(scheduleJob);
            logger.info("Done deleting schedule job with id: '{}'.", scheduleJob.getSourceInfoId());
            return new GenericResponseDTO<>(null, "SUCCESS");
        } catch (Exception e) {
            logger.debug("Exception was occurred while deleting schedule job with id: '{}'. Message: {}",
                    job.getSourceInfoId(), e.getMessage());
            return new GenericResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCHEDULING), null);
        }
    }

    @Override
    public GenericResponseDTO<String> runJob(SchedulerJobDTO job) {
        logger.debug("Running schedule job with id: '{}'...", job.getSourceInfoId());
        try {
            final ScheduleJob scheduleJob = mapper.map(job, ScheduleJob.class);
            scheduleJobService.runOnce(scheduleJob);
            logger.info("Done running schedule job with id: '{}'.", scheduleJob.getSourceInfoId());
            return new GenericResponseDTO<>(null, "SUCCESS");
        } catch (Exception e) {
            logger.debug("Exception was occurred while running schedule job with id: '{}'. Message: {}",
                    job.getSourceInfoId(), e.getMessage());
            return new GenericResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCHEDULING), null);
        }
    }


    //TODO write validation service for this class
    @Override
    public GenericResponseDTO<String> saveOrUpdate(SchedulerJobDTO job) {
        logger.debug("Storing or updating schedule job with id: '{}'...", job.getSourceInfoId());
        GenericResponseDTO<String> response;
        if (!job.getChannel().isActive()) {
            logger.debug("Channel:{} is inactive.", job.getChannel());
            return new GenericResponseDTO<>(endpointHelper.create("Source does not exist.", LoadingStage.SCHEDULING), null);
        }
        try {
            final ScheduleJob scheduleJob = mapper.map(job, ScheduleJob.class);
            scheduleJobService.save(scheduleJob);
            logger.info("Done storing or updating schedule job with id: '{}'.", scheduleJob.getSourceInfoId());
            response = new GenericResponseDTO<>(null, "SUCCESS");
        } catch (Exception e) {
            logger.debug("Exception was occurred while saving or updating schedule job with id: '{}'. Message: {}", job.getSourceInfoId(), e.getMessage());
            response = new GenericResponseDTO<>(endpointHelper.resolve(e, LoadingStage.SCHEDULING), null);
        }
        return response;
    }
}
