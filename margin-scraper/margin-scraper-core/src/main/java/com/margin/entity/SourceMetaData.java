package com.margin.entity;

import com.margin.enums.Channel;
import com.margin.enums.Country;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class SourceMetaData extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Country country;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Channel channel;

}
