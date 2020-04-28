package com.margin.entity.tools.api;

import com.margin.entity.tools.InfoResource;
import com.margin.entity.tools.SourceVersioningResource;

import java.io.Closeable;

public interface EntityToolsApiClient extends Closeable {

    InfoResource info();

    SourceVersioningResource sourceVersioning();

}
