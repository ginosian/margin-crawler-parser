package com.margin.dto.scheduler;

import com.margin.enums.Channel;
import com.margin.enums.Country;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class SchedulerJobDTO {
    private String jobId;
    private Long sourceInfoId;
    private Channel channel;
    private Country country;
    private String state;
    private String cronExpression;
    private String description;
    private Date nextFireDate;
}
