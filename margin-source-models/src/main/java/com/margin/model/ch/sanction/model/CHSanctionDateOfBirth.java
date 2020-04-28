package com.margin.model.ch.sanction.model;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "dateOfBirthItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class CHSanctionDateOfBirth {
    @XmlElement(name = "uid")
    private String uid;
    @XmlElement(name = "dateOfBirth")
    private String dateOfBirth;
    @XmlElement(name = "mainEntry")
    private String mainEntry;
}
