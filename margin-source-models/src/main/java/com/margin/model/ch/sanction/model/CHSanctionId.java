package com.margin.model.ch.sanction.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "id")
@XmlAccessorType(XmlAccessType.FIELD)
public class CHSanctionId {
    @XmlElement(name = "uid")
    private String uid;
    @XmlElement(name = "idType")
    private String idType;
    @XmlElement(name = "idNumber")
    private String idNumber;
    @XmlElement(name = "idCountry")
    private String idCountry;
}
