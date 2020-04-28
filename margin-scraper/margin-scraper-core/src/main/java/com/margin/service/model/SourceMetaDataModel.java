package com.margin.service.model;

import com.margin.enums.Channel;
import com.margin.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SourceMetaDataModel {
    private Long id;
    private Country country;
    private Channel channel;

    public SourceMetaDataModel(Country country, Channel channel) {
        this.country = country;
        this.channel = channel;
    }
}
