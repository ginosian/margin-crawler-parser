package com.margin.service.model;
/*
 *   @author ironman
 *   @since  11/1/18
 */

import com.margin.enums.Continent;
import com.margin.enums.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryResponse {
    private Long id;

    private Country name;

    private String isoCode;

    private Continent continent;

    private String unCode;

    private String dialingCode;

    private String currencyCode;

    private String currencySymbol;

    private String currencyName;

    private Integer rank;

    private Double overallScore;

    private String capital;
}
