package com.margin.model.jordan.registry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JordanRegistryBoardDirector {
    private String name;
    private String relationWithCompany;
    private String dateOfHiring;
    private String memberRepresentingCompany;
}
