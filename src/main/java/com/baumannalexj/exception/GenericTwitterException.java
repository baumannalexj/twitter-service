package com.baumannalexj.exception;

public class GenericTwitterException extends Exception {
    private int errorCode;
    private String twitterErrorMessage;

    public GenericTwitterException(int errorCode, String twitterErrorMessage) {
        super("Unhandled Twitter Exception.");
        this.errorCode = errorCode;
        this.twitterErrorMessage = twitterErrorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getTwitterErrorMessage() {
        return twitterErrorMessage;
    }

    public void setTwitterErrorMessage(String twitterErrorMessage) {
        this.twitterErrorMessage = twitterErrorMessage;
    }

}
