package com.margin.model;

import com.margin.enums.Channel;
import com.margin.enums.Country;
import com.margin.model.ru.bank.license.RuBankLicenseSourceModel;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RuBankLicenseSourceModel.class, name = "RuBankLicenseSourceModel"),
})
@Getter
@Setter
public class AbstractModel{
    private String _id;
    private Country country;
    private Channel channel;
    private Integer version;
}
