package com.margin.model.ukraine.pep;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UaPepPersonModel {
    private String firstName;
    private String firstNameEn;
    private String lastName;
    private String lastNameEn;
    private String middleName;
    private String middleNameEn;
    private String fullName;
    private String fullNameEn;
    private String names;

    private String lastJobTitle;
    private String lastJobTitleEn;
    private String lastWorkplace;
    private String lastWorkplaceEn;

    private String wiki;
    private String wikiEn;

    private String reputationSanctions;
    private String reputationSanctionsEn;
    private String reputationConvictions;
    private String reputationConvictionsEn;
    private String reputationAssets;
    private String reputationAssetsEn;
    private String reputationCrimes;
    private String reputationCrimesEn;
    private String reputationManhunt;
    private String reputationManhuntEn;

    private String dateOfBirth;
    private String cityOfBirth;
    private String cityOfBirthEn;

    private Boolean isPep;
    private String typeOfOfficial;
    private String typeOfOfficialEn;
    private String reasonOfTermination;
    private String reasonOfTerminationEn;
    private String terminationDate;

    private String linkToPhoto;
    private String linkToDossier;
    private String isDied;

    private Set<UaPepCompany> relatedCompanies;
    private Set<UaPepRelativePerson> relatedPersons;
    private Set<UaPepDeclaration> declarations;
    private Set<UaPepCountry> relatedCountries;
}
