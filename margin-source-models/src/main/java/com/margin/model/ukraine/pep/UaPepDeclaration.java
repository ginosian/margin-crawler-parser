package com.margin.model.ukraine.pep;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UaPepDeclaration {
    private String year;
    private String position;
    private String positionEn;
    private String linkToAssetsDeclaration;
    private String region;
    private String regionEn;
    private String nameOfPublicAuthority;
    private String nameOfPublicAuthorityEn;
    private Double assetsIncome;
    private Double familyIncome;
}
