package com.margin.service.model;
/*
 *   @author ironman
 *   @since  11/13/18
 */

import com.margin.enums.Country;
import com.margin.enums.RegionType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegionResponse {
    private Long id;

    private Country country;

    private String name;

    private RegionType type;
}
