package com.margin.endpoint.impl;

import com.margin.dto.info.InfoDTO;
import com.margin.endpoint.OldLoaderInfoEndpoint;

public class OldLoaderInfoEndpointImpl implements OldLoaderInfoEndpoint {

    @Override
    public InfoDTO startLoadingSource() {
        return new InfoDTO("Old Loader works just GREAT!");
    }
}
