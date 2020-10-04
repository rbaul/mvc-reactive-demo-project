package com.example.springwebfluxdemo.services.exceptions;

public class NotFoundException extends GeneralException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable e) {
        super(message, e);
    }

    public static NotFoundException createNotFoundException(long id) {
        return new NotFoundException(String.format("Not exist with id: %s", id));
    }
}
