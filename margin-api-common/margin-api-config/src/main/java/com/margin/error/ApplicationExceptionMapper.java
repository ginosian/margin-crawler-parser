package com.margin.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionMapper.class);

    @Override
    public Response toResponse(Exception e) {
        return createResponse(new ApiException(e.getMessage()));
    }

    public static Response createResponse(ApiException exception) {
        return Response.status(exception.getResponseStatusCode()).entity(exception).type(MediaType.APPLICATION_JSON)
                .build();
    }
}
