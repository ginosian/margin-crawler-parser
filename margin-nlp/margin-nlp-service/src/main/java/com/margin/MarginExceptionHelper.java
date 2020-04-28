package com.margin;

import com.margin.enums.LoadingStage;
import com.margin.error.ApiException;
import org.springframework.stereotype.Component;

@Component
public class MarginExceptionHelper {
    public ApiException create(final String message, final LoadingStage loadingStage){
        final ApiException exception = new ApiException(message);
        exception.setStage(loadingStage);
        return exception;
    }

    public ApiException resolve(final Exception exception, final LoadingStage loadingStage){
        if(exception instanceof ApiException){
            return (ApiException) exception;
        } else {
            return create(exception.getMessage(), loadingStage);
        }
    }
}
