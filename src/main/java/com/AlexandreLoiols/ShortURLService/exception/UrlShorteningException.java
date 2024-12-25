package com.AlexandreLoiols.ShortURLService.exception;

public class UrlShorteningException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UrlShorteningException(String message) {
        super(message);
    }

    public UrlShorteningException(String message, Throwable cause) {
        super(message);
    }
}