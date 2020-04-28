package com.margin.error;

import javax.ws.rs.core.Response;
import java.io.Serializable;

public interface MarginApiError extends Serializable {
    String getDefaultMessage();

    Response.Status getResponseHttpStatus();

    long getErrorCode();

    Class<? extends ApiException> getExceptionClass();
}
