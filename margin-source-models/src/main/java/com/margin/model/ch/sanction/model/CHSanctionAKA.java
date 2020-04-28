package com.margin.model.ch.sanction.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "aka")
@XmlAccessorType(XmlAccessType.FIELD)
public class CHSanctionAKA {
    @XmlElement(name = "uid")
    private String uid;
    @XmlElement(name = "type")
    private String type;
    @XmlElement(name = "category")
    private String category;
    @XmlElement(name = "lastName")
    private String lastName;
    @XmlElement(name = "firstName")
    private String firstName;
}
