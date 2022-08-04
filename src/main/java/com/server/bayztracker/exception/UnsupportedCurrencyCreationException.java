package com.server.bayztracker.exception;

public class UnsupportedCurrencyCreationException extends RuntimeException {

    public UnsupportedCurrencyCreationException(String message) {
        throw new RuntimeException(message);
    }
}
