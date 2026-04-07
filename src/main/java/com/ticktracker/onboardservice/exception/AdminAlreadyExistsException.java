package com.ticktracker.onboardservice.exception;

public class AdminAlreadyExistsException extends RuntimeException{

    public AdminAlreadyExistsException(String message)
    {
        super(message);

    }
}
