package com.margin.service.model;
/*
 *   @author ironman
 *   @since  11/16/18
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionUpdateRequest extends RegionCreationRequest {
    private Long id;
}
