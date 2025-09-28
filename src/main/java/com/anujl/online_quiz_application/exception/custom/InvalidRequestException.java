package com.anujl.online_quiz_application.exception.custom;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}