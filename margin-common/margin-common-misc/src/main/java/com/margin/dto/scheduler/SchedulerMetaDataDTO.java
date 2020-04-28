package com.margin.dto.scheduler;

import com.margin.enums.Channel;
import com.margin.enums.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchedulerMetaDataDTO {
    private Channel channel;
    private Country country;
}
