package com.margin.entity;
/*
 *   @author ironman
 *   @since  11/1/18
 */

import com.margin.enums.Continent;
import com.margin.enums.Country;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Country")
public class CountryEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Country name;

    @Column(nullable = false)
    private String isoCode;

    @Column
    private Continent continent;

    @Column
    private String unCode;

    @Column
    private String dialingCode;

    @Column
    private String currencyCode;

    @Column
    private String currencySymbol;

    @Column
    private String currencyName;

    @Column
    private int rank;

    @Column
    private double overallScore;

    @Column
    private String capital;
}
