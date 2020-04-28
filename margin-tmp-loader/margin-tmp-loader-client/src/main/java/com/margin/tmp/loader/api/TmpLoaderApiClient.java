package com.margin.tmp.loader.api;

import com.margin.tmp.loader.InfoResource;
import com.margin.tmp.loader.TmpLoaderApiResource;

public interface TmpLoaderApiClient {
    InfoResource info();
    TmpLoaderApiResource tmpLoader();
}
