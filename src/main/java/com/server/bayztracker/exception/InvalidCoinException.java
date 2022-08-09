package com.server.bayztracker.exception;

public class InvalidCoinException extends RuntimeException{

    public InvalidCoinException(String message) {
        throw new RuntimeException(message);
    }
}
