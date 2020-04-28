package com.margin.model.ukraine.registry;

import com.margin.model.AbstractModel;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class UA_RegistryModel extends AbstractModel {

    @XmlElement(name = "NAME")
    private String name;

    @XmlElement(name = "SHORT_NAME")
    private String shortName;

    @XmlElement(name = "EDRPOU")
    private String edrpou;

    @XmlElement(name = "ADDRESS")
    private String address;

    @XmlElement(name = "BOSS")
    private String boss;

    @XmlElement(name = "KVED")
    private String kved;

    @XmlElement(name = "STAN")
    private String stan;

    @XmlElementWrapper(name = "FOUNDERS")
    @XmlElement(name = "FOUNDER")
    private List<UKR_FounderModel> founders;
}
