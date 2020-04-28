package com.margin.model.jordan.registry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JordanRegistryModel {

    private String registrationNumber;
    private String localId;
    private String name;
    private String profile;
    private String registrationDate;
    private String notes;
    private String lastModificationDate;
    private String status;
    private String type;
    private String companyCenter;
    private String mobileNumber;
    private String fax;
    private String phone;
    private String governorate;
    private String email;
    private String city;
    private String postalCode;
    private String postalBox;
    private String share;
    private String capitalUponRegistration;
    private String authorizedCapital;
    private String shareInKind;
    private String stoackShareValue;
    private String cashShare;
    private String subscribedCapital;
    private String paidCapital;
    private String currentCapital;

    private JordanRegistryParentInfo parentInfo;
    private Set<JordanRegistryBoardMember> boardMembers;
    private Set<JordanRegistryBoardDirector> boardDirectors;
    private Set<JordanRegistryPartner> partners;
}
