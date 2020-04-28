package com.margin.model.ch.sanction.entity;

import com.margin.model.ch.sanction.model.*;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "sdnEntry")
@XmlAccessorType(XmlAccessType.FIELD)
public class CHSanctionSourceEntity {

    //    private CHSanctionUniModel uni;
    @XmlElement(name = "uid")
    private String uid;
    @XmlElement(name = "firstName")
    private String firstName;
    @XmlElement(name = "lastName")
    private String lastName;
    @XmlElement(name = "sdnType")
    private String sdnType;

    @XmlElement(name = "remarks")
    private String remarks;

    @XmlElement(name = "programList")
    private List<CHSanctionProgram> programList;

    @XmlElementWrapper(name = "idList")
    @XmlElement(name = "id")
    private List<CHSanctionId> idList;

    @XmlElementWrapper(name = "akaList")
    @XmlElement(name = "aka")
    private List<CHSanctionAKA> akaList;

    @XmlElementWrapper(name = "addressList")
    @XmlElement(name = "address")
    private List<CHSanctionAddress> addressList;

    @XmlElementWrapper(name = "dateOfBirthList")
    @XmlElement(name = "dateOfBirthItem")
    private List<CHSanctionDateOfBirth> dateOfBirthList;

    @XmlElementWrapper(name = "placeOfBirthList")
    @XmlElement(name = "placeOfBirthItem")
    private List<CHSanctionPlaceOfBirth> placeOfBirthItem;

    @XmlElementWrapper(name = "citizenshipList")
    @XmlElement(name = "citizenship")
    private List<CHSanctionCitizenship> citizenshipList;



}
