package com.margin.endpoint;

import com.margin.error.ApiException;
import com.margin.enums.LoadingStage;
import org.springframework.stereotype.Component;

@Component
public class EndpointHelper {

    public ApiException create(final String message, final LoadingStage loadingStage){
        final ApiException exception = new ApiException(message);
        exception.setStage(LoadingStage.SCRAPING);
        return exception;
    }

    public ApiException resolve(final Exception e, final LoadingStage stage){
        if(e instanceof ApiException){
            return (ApiException) e;
        } else {
            return create(e.getMessage(), stage);
        }
    }
}
