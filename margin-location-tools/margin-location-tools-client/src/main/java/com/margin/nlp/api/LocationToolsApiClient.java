package com.margin.nlp.api;


import com.margin.nlp.InfoResource;

import java.io.Closeable;

public interface LocationToolsApiClient extends Closeable {

    InfoResource info();
}
