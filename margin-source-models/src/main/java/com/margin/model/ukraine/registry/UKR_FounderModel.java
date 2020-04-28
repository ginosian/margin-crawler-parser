package com.margin.model.ukraine.registry;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "FOUNDERS")
@XmlAccessorType(XmlAccessType.FIELD)
public class UKR_FounderModel {

    @XmlElement(name = "FOUNDER")
    private String founder;
}
