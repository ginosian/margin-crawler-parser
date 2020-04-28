package com.margin.io.tools.xml;

import com.margin.model.ch.sanction.entity.CHSanctionSourceEntity;
import com.margin.model.ch.sanction.model.CHSanctionPublishInformation;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sdnList")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Entry {

    @XmlElement(name = "publshInformation")
    private CHSanctionPublishInformation publishInformation;


    @XmlElement(type = CHSanctionSourceEntity.class, name = "sdnEntry")
    private List<CHSanctionSourceEntity> content;

}