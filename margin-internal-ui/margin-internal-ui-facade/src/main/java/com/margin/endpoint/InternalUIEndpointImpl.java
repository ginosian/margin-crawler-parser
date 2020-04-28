package com.margin.endpoint;

import com.margin.dto.GenericListResponseDTO;
import com.margin.dto.GenericResponseDTO;
import com.margin.dto.scheduler.SchedulerJobDTO;
import com.margin.dto.scheduler.SchedulerMetaDataDTO;
import com.margin.scheduler.job.api.SchedulerApiClient;
import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static com.cronutils.model.CronType.QUARTZ;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/internal/ui/v1/schedule",
        consumes = {APPLICATION_JSON_VALUE},
        produces = {APPLICATION_JSON_VALUE})
public class InternalUIEndpointImpl {
    private static final Logger logger = LoggerFactory.getLogger(InternalUIEndpointImpl.class);

    @Autowired
    private SchedulerApiClient schedulerApiClient;

    @GetMapping("/metaData")
    public Object metaData() {
        logger.debug("Retrieving metadata for scheduler...");
        final GenericResponseDTO<SchedulerMetaDataDTO> schedulerMetaData = schedulerApiClient.scheduler().metaData();
        logger.info("Done retrieving metadata for scheduler with country: '{}' and channel: '{}'.",
                schedulerMetaData.getResponse().getCountry(), schedulerMetaData.getResponse().getChannel());
        return schedulerMetaData;
    }

    @GetMapping("/all")
    public Object getAllJobs() {
        logger.debug("Getting all jobs for scheduler...");
        final CronDescriptor descriptor = CronDescriptor.instance(Locale.UK);
        final CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);
        final CronParser parser = new CronParser(cronDefinition);
        final GenericListResponseDTO<SchedulerJobDTO> allJobs = schedulerApiClient.scheduler().getAll();
        allJobs.getItems().forEach(schedulerJobDTO -> schedulerJobDTO.setDescription(descriptor.describe(parser.parse(schedulerJobDTO.getCronExpression()))));

        logger.info("Done getting {} jobs for scheduler.", allJobs.getTotalItems());
        return allJobs;
    }

    @GetMapping("/all-running")
    public Object getRunningJobs() {
        logger.debug("Getting all running jobs for scheduler...");
        final CronDescriptor descriptor = CronDescriptor.instance(Locale.UK);
        final CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);
        final CronParser parser = new CronParser(cronDefinition);
        final GenericListResponseDTO<SchedulerJobDTO> runningJobs = schedulerApiClient.scheduler().getRunningJobs();
        runningJobs.getItems().forEach(schedulerJobDTO -> schedulerJobDTO.setDescription(descriptor.describe(parser.parse(schedulerJobDTO.getCronExpression()))));
        logger.info("Done getting {} running jobs for scheduler.", runningJobs.getTotalItems());
        return runningJobs;
    }

    @PostMapping(value = "/pause")
    public Object pauseJob(@RequestBody SchedulerJobDTO job) {
        logger.debug("Pausing job with id: '{}'...", job.getSourceInfoId());
        final GenericResponseDTO<String> response = schedulerApiClient.scheduler().pauseJob(job);
        logger.info("Done pausing job with id: '{}'.", job.getSourceInfoId());
        return response;
    }

    @PostMapping(value = "/resume")
    public Object resumeJob(@RequestBody SchedulerJobDTO job) {
        logger.debug("Resuming job with id: '{}'...", job.getSourceInfoId());
        final GenericResponseDTO<String> response = schedulerApiClient.scheduler().resumeJob(job);
        logger.info("Done resuming job with id: '{}'.", job.getSourceInfoId());
        return response;
    }

    @PostMapping(value = "/delete")
    public Object deleteJob(@RequestBody SchedulerJobDTO job) {
        logger.debug("Deleting job with id: '{}'...", job.getSourceInfoId());
        final GenericResponseDTO<String> response = schedulerApiClient.scheduler().deleteJob(job);
        logger.info("Done deleting job with id: '{}'.", job.getSourceInfoId());
        return response;
    }

    @PostMapping(value = "/run")
    public Object runJob(@RequestBody SchedulerJobDTO job) {
        logger.debug("Running job with id: '{}'...", job.getSourceInfoId());
        final GenericResponseDTO<String> response = schedulerApiClient.scheduler().runJob(job);
        logger.info("Done running job with id: '{}'.", job.getSourceInfoId());
        return response;
    }

    @PostMapping(value = "/save")
    public Object saveOrUpdate(@RequestBody SchedulerJobDTO job) {
        logger.debug("Saving or updating job with id: '{}'...", job.getSourceInfoId());
        final GenericResponseDTO<String> response = schedulerApiClient.scheduler().saveOrUpdate(job);
        logger.info("Done saving or updating job with id: '{}'.",
                job.getSourceInfoId(), response.getResponse());
        return response;
    }
}
