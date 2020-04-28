package com.margin.entity;
/*
 *   @author ironman
 *   @since  11/9/18
 */

import com.margin.enums.RegionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Region {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CountryEntity countryEntity;

    @Column
    @Enumerated(EnumType.STRING)
    private RegionType type;
}
