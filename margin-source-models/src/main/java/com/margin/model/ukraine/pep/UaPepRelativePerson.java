package com.margin.model.ukraine.pep;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UaPepRelativePerson {
    private String relationshipType;
    private String fullName;
    private String fullNameEn;
    private Boolean isPep;
}
