package com.margin.job;

import com.margin.enums.Channel;
import com.margin.enums.Country;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class ScheduleJob implements Serializable {
    private String jobId;
    private Long sourceInfoId;
    private Channel channel;
    private Country country;
    private String state;
    private String cronExpression;
    private String description;
    private Date nextFireDate;
}
