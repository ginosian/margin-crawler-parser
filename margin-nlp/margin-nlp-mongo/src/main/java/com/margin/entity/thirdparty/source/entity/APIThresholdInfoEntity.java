package com.margin.entity.thirdparty.source.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class APIThresholdInfoEntity {
    @Field(value = "monthly_limit_dollar")
    private Integer monthlyLimitDollar;
}
