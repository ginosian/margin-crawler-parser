package com.margin.service;

import com.margin.job.QuartzJobFactory;
import com.margin.job.ScheduleJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.util.Assert.notNull;

//import com.margin.mq.Sender;

@Service
public class SourceVersioningScheduleJobServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(SourceVersioningScheduleJobServiceImpl.class);

    @Autowired
    @Qualifier(value = "com.margin_scheduler")
    private Scheduler scheduler;

    public List<ScheduleJob> getAll() {
        final List<ScheduleJob> jobList = new ArrayList<>();
        logger.trace("Retrieving all schedule jobs...");
        try {
            final GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeySet = scheduler.getJobKeys(matcher);
            for (JobKey jobKey : jobKeySet) {
                final JobDataMap jobDataMap = scheduler.getJobDetail(jobKey).getJobDataMap();
                final ScheduleJob scheduleJob = (ScheduleJob) jobDataMap.get(jobKey.getName());
                final Trigger trigger = scheduler.getTrigger(new TriggerKey(jobKey.getName(), jobKey.getGroup()));
                final Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                scheduleJob.setState(triggerState.name());
                scheduleJob.setNextFireDate(trigger.getNextFireTime());
                scheduleJob.setCronExpression(((CronTrigger)trigger).getCronExpression());
                jobList.add(scheduleJob);
            }
            logger.debug("Done retrieving {} schedule jobs.", jobList.size());
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.debug("Exception was occurred while retrieving all schedule jobs: {}.", e.getMessage());
        }
        return jobList;
    }


    public List<ScheduleJob> getAllRunning() {
        final List<ScheduleJob> scheduleJobs = new ArrayList<>();
        logger.trace("Retrieving all running schedule jobs...");
        try {
            final List<JobExecutionContext> executingJobList = scheduler.getCurrentlyExecutingJobs();
            if (executingJobList != null) {
                for(JobExecutionContext context : executingJobList){
                    final JobKey jobKey = context.getJobDetail().getKey();
                    final JobDataMap jobDataMap = (JobDataMap) context.getMergedJobDataMap().get(jobKey.getName());
                    final Trigger trigger = scheduler.getTrigger(new TriggerKey(jobKey.getName(), jobKey.getGroup()));
                    final Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    final ScheduleJob scheduleJob = (ScheduleJob) jobDataMap.get(jobKey.getName());
                    scheduleJob.setState(triggerState.name());
                    scheduleJob.setNextFireDate(trigger.getNextFireTime());
                    scheduleJob.setCronExpression(((CronTrigger)trigger).getCronExpression());
                    scheduleJobs.add(scheduleJob);
                }
                logger.debug("Done retrieving {} running schedule jobs.", scheduleJobs.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("Exception was occurred while retrieving all running schedule jobs: {}.", e.getMessage());
        }
        return scheduleJobs;
    }


    public void save(final ScheduleJob scheduleJob) throws Exception {
        validate(scheduleJob);
        logger.trace("Storing or updating schedule job with id: '{}'...", scheduleJob.getSourceInfoId());
        final TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getChannel().name(), scheduleJob.getCountry().name());
        final CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if(scheduler.checkExists(new JobKey(scheduleJob.getChannel().name(), scheduleJob.getCountry().name()))
        && trigger != null){
            updateJobCronExpression(scheduleJob);
        } else {
            add(scheduleJob);
        }
        logger.debug("Done storing or updating schedule job with id: '{}'.", scheduleJob.getSourceInfoId());
    }

    private void add(ScheduleJob scheduleJob) throws Exception {
        validate(scheduleJob);
        logger.trace("Storing schedule job...");
        JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity(scheduleJob.getChannel().name(), scheduleJob.getCountry().name()).build();
        jobDetail.getJobDataMap().put(scheduleJob.getChannel().name(), scheduleJob);
        final CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
        final CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(scheduleJob.getChannel().name(), scheduleJob.getCountry().name()).withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
        logger.debug("Done storing the schedule job: '{}'.", jobDetail.getDescription());
    }


    public void pause(ScheduleJob scheduleJob) throws SchedulerException {
        validate(scheduleJob);
        logger.trace("Pausing schedule job with id: '{}'...", scheduleJob.getSourceInfoId());
        JobKey jobKey = JobKey.jobKey(scheduleJob.getChannel().name(), scheduleJob.getCountry().name());
        scheduler.pauseJob(jobKey);
        logger.debug("Done pausing schedule job with id: '{}'.", scheduleJob.getSourceInfoId());
    }

    public void resume(ScheduleJob scheduleJob) throws SchedulerException {
        validate(scheduleJob);
        logger.trace("Resuming schedule job with id: '{}'...", scheduleJob.getSourceInfoId());
        JobKey jobKey = JobKey.jobKey(scheduleJob.getChannel().name(), scheduleJob.getCountry().name());
        scheduler.resumeJob(jobKey);
        logger.debug("Done resuming schedule job with id: '{}'.", scheduleJob.getSourceInfoId());
    }

    public void delete(ScheduleJob scheduleJob) throws SchedulerException {
        validate(scheduleJob);
        logger.trace("Deleting schedule job with id: '{}'...", scheduleJob.getSourceInfoId());
        JobKey jobKey = JobKey.jobKey(scheduleJob.getChannel().name(), scheduleJob.getCountry().name());
        scheduler.deleteJob(jobKey);
        logger.debug("Done deleting schedule job with id: '{}'.", scheduleJob.getSourceInfoId());
    }

    public void runOnce(ScheduleJob scheduleJob) throws SchedulerException {
        //TODO check if job really does exist
        validate(scheduleJob);
        logger.trace("One time running schedule job with id: '{}'...", scheduleJob.getSourceInfoId());
        JobKey jobKey = JobKey.jobKey(scheduleJob.getChannel().name(), scheduleJob.getCountry().name());
        scheduler.triggerJob(jobKey);
        logger.debug("Done one time running schedule job with id: '{}'.", scheduleJob.getSourceInfoId());
    }


    private void updateJobCronExpression(ScheduleJob scheduleJob) throws SchedulerException {
        validate(scheduleJob);
        notNull(scheduleJob.getCronExpression(), "CronExpression is can not be null");
        logger.trace("Updating schedule job with id: '{}' and trigger: '{}'...", scheduleJob.getSourceInfoId(), scheduleJob.getCronExpression());
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getChannel().name(), scheduleJob.getCountry().name());
        CronTriggerImpl trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
        try {
            trigger.setCronExpression(scheduleJob.getCronExpression());
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        logger.debug("Done updating schedule job with id: '{}' and trigger: '{}'.", scheduleJob.getSourceInfoId(), scheduleJob.getCronExpression());
    }

    private void validate(ScheduleJob scheduleJob) {
        notNull(scheduleJob, "ScheduleJob can not be null");
        notNull(scheduleJob.getSourceInfoId(), "Source Info id can not be null");
        notNull(scheduleJob.getCountry(), "Country can not be null");
        notNull(scheduleJob.getChannel(), "Channel can not be null");
        notNull(scheduleJob.getCronExpression(), "Cron expresion can not be null");
    }
}
