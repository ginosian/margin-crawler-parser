package com.margin.loader.service.impl;

import com.margin.dto.tmp.loader.LoaderDTO;
import com.margin.enums.LoadingStage;
import com.margin.error.ApiException;
import com.margin.loader.service.OldStyleDBLoaderService;
import com.margin.model.AbstractModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static org.springframework.util.Assert.hasText;

@Service
public class OldStyleDBLoaderServiceImpl implements OldStyleDBLoaderService {
    private static final Logger logger = LoggerFactory.getLogger(OldStyleDBLoaderServiceImpl.class);


    @Override
    public LoaderDTO loadNode(final AbstractModel abstractModel) {
        Assert.notNull(abstractModel, "abstract model can not be null");
        Assert.notNull(abstractModel.getChannel(), "channel can not be null");
        LoaderDTO responseDTO = null;
                responseDTO = new LoaderDTO(abstractModel.get_id());
        return responseDTO;
    }

    @Override
    public LoaderDTO startLoadingSource(final String databaseName) {
        hasText(databaseName, "database name can not be null or empty.");
        return new LoaderDTO();
    }

    @Override
    public LoaderDTO finishLoadingSource(final String databaseName) {
        hasText(databaseName, "database name can not be null or empty.");
        return new LoaderDTO();
    }

    private String getClassName(AbstractModel abstractModel) {
        return "com.margin.loader.converter.impl."
                + String.format("%s_%s_Converter", abstractModel.getCountry().getISO_3166_code(), abstractModel.getChannel().name());
    }

    private ApiException create(final String message, final LoadingStage loadingStage) {
        final ApiException exception = new ApiException(message);
        exception.setStage(loadingStage);
        return exception;
    }
}
