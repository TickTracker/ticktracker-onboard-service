package com.ticktracker.onboardservice.exception;

import com.ticktracker.onboardservice.model.User;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message)
    {
        super(message);
    }
}
