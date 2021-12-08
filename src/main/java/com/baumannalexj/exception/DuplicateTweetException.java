package com.baumannalexj.exception;

public class DuplicateTweetException extends Exception{
    public DuplicateTweetException() {
            super("Duplicate status -- cannot post again.");
        }

    public DuplicateTweetException(String message) {
            super(message);
        }
}
