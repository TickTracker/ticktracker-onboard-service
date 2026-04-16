package com.ticktracker.onboardservice.exception;

public class RefreshTokenExpiredException extends RuntimeException{

    public RefreshTokenExpiredException(String message)
    {
        super(message);
    }
}
