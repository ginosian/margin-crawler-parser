package com.margin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class IndexInfo {
    private Long index;
    private String context;
}
