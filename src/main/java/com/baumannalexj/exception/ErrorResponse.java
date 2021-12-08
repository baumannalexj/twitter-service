package com.baumannalexj.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {

    private int code;
    private String message;
    private String cause;
    private String stackTrack;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getStackTrack() {
        return stackTrack;
    }

    public void setStackTrack(String stackTrack) {
        this.stackTrack = stackTrack;
    }
}