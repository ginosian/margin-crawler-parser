package com.margin.model.ch.sanction.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "publshInformation")
@XmlAccessorType(XmlAccessType.FIELD)
public class CHSanctionPublishInformation {

    @XmlElement(name = "Publish_Date")
    private String publishDate;
    @XmlElement(name = "Record_Count")
    private String recordCount;
}
