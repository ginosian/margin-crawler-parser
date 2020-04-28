package com.margin.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DtoValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger logger = LoggerFactory.getLogger(DtoValidationExceptionMapper.class);

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        logger.debug("Dto validation exception has occurred", exception.toString());

        StringBuilder message = new StringBuilder();
        exception.getConstraintViolations().forEach(e -> {
            String fieldName = e.getPropertyPath().toString();
            String msg = e.getMessage();
            message.append(", ").append(fieldName).append(" ").append(msg);
        });

        return ApplicationExceptionMapper.createResponse(
                new ApiException(message.toString())
        );
    }
}
