package com.margin.job;

import com.margin.helper.RabbitTemplateHelper;
import com.margin.scraper.api.ScraperApiClient;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DisallowConcurrentExecution
@Component
public class QuartzJobFactory implements Job {

    @Autowired
    private RabbitTemplateHelper rabbitTemplate;

    @Autowired
    private ScraperApiClient scraperApiClient;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        final JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        final ScheduleJob scheduleJob = (ScheduleJob) jobDataMap.get(jobExecutionContext.getJobDetail().getKey().getName());
        scraperApiClient.loader().load(scheduleJob.getSourceInfoId());
    }

}
