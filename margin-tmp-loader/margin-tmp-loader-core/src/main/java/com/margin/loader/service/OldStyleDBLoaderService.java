package com.margin.loader.service;

import com.margin.model.AbstractModel;
import com.margin.dto.tmp.loader.LoaderDTO;

public interface OldStyleDBLoaderService {

    LoaderDTO loadNode(AbstractModel abstractModel);

    LoaderDTO startLoadingSource(String databaseName);

    LoaderDTO finishLoadingSource(String databaseName);

}
