package com.server.bayztracker.exception;

public class UnsupportedCurrencyCreationException extends RuntimeException {

    UnsupportedCurrencyCreationException(String message, Throwable cause) {
        throw new RuntimeException(message, cause);
    }
}
