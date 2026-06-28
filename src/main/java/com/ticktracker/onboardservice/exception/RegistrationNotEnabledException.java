package com.ticktracker.onboardservice.exception;

public class RegistrationNotEnabledException extends RuntimeException{

    public RegistrationNotEnabledException(String message)
    {
        super(message);
    }
}
