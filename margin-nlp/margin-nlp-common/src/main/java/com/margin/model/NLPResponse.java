package com.margin.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NLPResponse {
    private NLPDataResponse localResponse;
    private NLPDataResponse thirdPartyResponse;
}
