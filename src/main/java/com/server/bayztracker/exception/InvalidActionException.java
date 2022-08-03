package com.server.bayztracker.exception;

public class InvalidActionException extends RuntimeException{

    public InvalidActionException(String message) {
        throw new RuntimeException(message);
    }

}
