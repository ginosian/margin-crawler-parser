package com.margin.model.ch.sanction.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "citizenship")
@XmlAccessorType(XmlAccessType.FIELD)
public class CHSanctionCitizenship {

    @XmlElement
    private String uid;
    @XmlElement
    private String country;
    @XmlElement
    private String mainEntry;
}
