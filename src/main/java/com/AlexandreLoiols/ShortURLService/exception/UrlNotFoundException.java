package com.AlexandreLoiols.ShortURLService.exception;

import jakarta.persistence.EntityNotFoundException;

public class UrlNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public UrlNotFoundException(String message) {
        super(message);
    }

    public UrlNotFoundException(String message, Throwable cause) {
        super(message);
    }
}