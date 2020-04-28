package com.margin.listener;

import com.margin.enums.Channel;
import com.margin.enums.Country;
import com.margin.job.ScheduleJob;
import com.margin.service.SourceVersioningScheduleJobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {

    @Autowired
    private SourceVersioningScheduleJobServiceImpl scheduleJobService;


    //
    @EventListener({ContextRefreshedEvent.class})
    public void onContextRefreshedEvent() {
        final ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setJobId("2");
        scheduleJob.setSourceInfoId(2L);
        scheduleJob.setChannel(Channel.RU_BANK_LICENSE);
        scheduleJob.setCountry(Country.RUSSIA);
        final String cron1 = "0 */3 * ? * *";
        scheduleJob.setCronExpression(cron1);
        scheduleJob.setDescription("Some");
        try {
            scheduleJobService.save(scheduleJob);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
