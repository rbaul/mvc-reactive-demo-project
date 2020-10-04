package com.example.springwebfluxdemo.services.exceptions;

/**
 * General Exception
 */
public class GeneralException extends RuntimeException {

    public GeneralException(String message) {
        super(message);
    }

    public GeneralException(String message, Throwable e) {
        super(message, e);
    }

}
