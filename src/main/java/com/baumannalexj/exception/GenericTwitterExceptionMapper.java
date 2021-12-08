package com.baumannalexj.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericTwitterExceptionMapper extends Exception implements ExceptionMapper<GenericTwitterException> {

    @Override
    public Response toResponse(GenericTwitterException exception) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(exception.getErrorCode());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setCause(exception.getTwitterErrorMessage());

        return Response
                .status(errorResponse.getCode())
                .entity(exception)
                .type("text/plain").build();
    }

}
