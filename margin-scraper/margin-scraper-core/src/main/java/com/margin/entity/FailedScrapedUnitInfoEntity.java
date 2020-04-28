package com.margin.entity;

import com.margin.enums.LoadingStage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
public class FailedScrapedUnitInfoEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column
    private LoadingStage stage;

    @Column
    private Integer errorCode;

    @Column
    private String errorMessage;

}
