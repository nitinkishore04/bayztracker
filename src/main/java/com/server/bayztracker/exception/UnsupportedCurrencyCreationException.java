package com.server.bayztracker.exception;

public class UnsupportedCurrencyCreationException extends RuntimeException {

    public UnsupportedCurrencyCreationException(String message) {
        throw new RuntimeException(message);
    }
    public UnsupportedCurrencyCreationException(String message, Throwable cause) {
        throw new RuntimeException(message, cause);
    }
}
