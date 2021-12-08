package com.baumannalexj.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
public class DuplicateTweetExceptionMapper extends Exception implements ExceptionMapper<DuplicateTweetException> {

    @Override
    public Response toResponse(DuplicateTweetException exception) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(403);
        errorResponse.setMessage(exception.getMessage());

        return Response
                .status(403)
                .entity(exception)
                .type(APPLICATION_JSON).build();
    }

}
