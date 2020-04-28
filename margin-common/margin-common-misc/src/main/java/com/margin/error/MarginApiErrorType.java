package com.margin.error;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum MarginApiErrorType implements MarginApiError {

    SUCCESS(10200, Response.Status.OK),
    UNEXPECTED_INTERNAL_ERROR(10500, Response.Status.INTERNAL_SERVER_ERROR),
    SERVICE_UNAVAILABLE(10503, Response.Status.SERVICE_UNAVAILABLE, "Service Unavailable"),
    INVALID_REQUEST_DATA(11001, Response.Status.NOT_ACCEPTABLE, "Invalid request"),
    RESOURCE_NOT_FOUND(11002, Response.Status.NOT_FOUND, "Resource not found"),
    UNAUTHORIZED(11003, Response.Status.UNAUTHORIZED, "The API client is not authorised"),
    FORBIDDEN(11004, Response.Status.FORBIDDEN, "The API client does not have access"),

    // Client errors
    FAILED_TO_PARSE_RESPONSE(12001, Response.Status.INTERNAL_SERVER_ERROR, "Failed to parse the response sent by the server"),
    UNKNOWN_CLIENT_ERROR(12002, Response.Status.INTERNAL_SERVER_ERROR, "Unknown error during server response processing");

    private static final Map<Long, Class<? extends ApiException>> exceptionClasses;
    private static volatile boolean initializationComplete;

    static {
        Map<Long, Class<? extends ApiException>> exceptionClassesTmp = new HashMap<>(MarginApiErrorType.values().length);
        for (MarginApiErrorType error : MarginApiErrorType.values()) {
            exceptionClassesTmp.put(error.getErrorCode(), error.getExceptionClass());
        }

        exceptionClasses = Collections.unmodifiableMap(exceptionClassesTmp);
        initializationComplete = true;
    }

    private final long errorCode;
    private final Response.Status responseHttpStatus;
    private final String defaultMessage;
    private final Class<? extends ApiException> exceptionClass;

    MarginApiErrorType(long errorCode, Response.Status responseHttpStatus, String defaultMessage, Class<? extends ApiException> clazz) {
        this.errorCode = errorCode;
        this.responseHttpStatus = responseHttpStatus;
        this.defaultMessage = defaultMessage;
        this.exceptionClass = clazz;
    }

    MarginApiErrorType(long errorCode, Response.Status responseHttpStatus, String defaultMessage) {
        this(errorCode, responseHttpStatus, defaultMessage, null);

    }

    MarginApiErrorType(long errorCode, Response.Status responseHttpStatus) {
        this(errorCode, responseHttpStatus, null);
    }

    @Override
    public long getErrorCode() {
        return errorCode;
    }

    @Override
    public Response.Status getResponseHttpStatus() {
        return responseHttpStatus;
    }

    @Override
    public String getDefaultMessage() {
        return defaultMessage;
    }

    @Override
    public Class<? extends ApiException> getExceptionClass() {
        return exceptionClass;
    }

    public static Class<? extends ApiException> getExceptionClass(long errorCode) {
        if (!initializationComplete) {
            throw new IllegalStateException("Initialization of the exception mapping hasn't yet completed!");
        }
        return exceptionClasses.get(errorCode);
    }
}
