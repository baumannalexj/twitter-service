package com.baumannalexj.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TwitterAccountNotFoundException extends Exception implements ExceptionMapper<TwitterAccountNotFoundException> {
    public TwitterAccountNotFoundException() {
        super("Account not found for Id given.");
    }

    public TwitterAccountNotFoundException(String message) {
        super(message);
    }

    @Override
    public Response toResponse(TwitterAccountNotFoundException exception) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(404);
        errorResponse.setMessage(exception.getMessage());

        return Response.status(404)
                .entity(exception)
                .type("text/plain").build();
    }

}
