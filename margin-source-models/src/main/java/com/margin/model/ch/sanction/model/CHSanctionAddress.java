package com.margin.model.ch.sanction.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class CHSanctionAddress {
    @XmlElement
    private String uid;
    @XmlElement
    private String stateOrProvince;
    @XmlElement
    private String address1;
    @XmlElement
    private String address2;
    @XmlElement
    private String address3;
    @XmlElement
    private String city;
    @XmlElement
    private String postalCode;
    @XmlElement
    private String country;
}
