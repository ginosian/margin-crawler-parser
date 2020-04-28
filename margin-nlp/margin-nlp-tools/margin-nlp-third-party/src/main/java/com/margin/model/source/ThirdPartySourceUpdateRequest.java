package com.margin.model.source;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThirdPartySourceUpdateRequest extends ThirdPartySourceCreationRequest{
    private String id;
}
