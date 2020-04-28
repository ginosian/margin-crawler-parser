package com.margin.model.ukraine.pep;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UaPepCompany {
    private String relationshipType;
    private String relationshipTypeEn;
    private String startDateOfConnection;
    private String endDateOfConnection;
    private String dateOfConfirmation;
    private String title;
    private String titleEn;
    private String shortTitle;
    private String shortTitleEn;
    private String taxNumber;
    private Boolean isState;

}
