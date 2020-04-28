package com.margin.event.loading.message;

import com.margin.enums.Channel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleChannelLoadingQueueMessage {

    private Long scrapingSourceId;
    private Channel channel;
}
