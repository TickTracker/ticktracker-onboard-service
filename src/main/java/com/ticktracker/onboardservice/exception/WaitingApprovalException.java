package com.ticktracker.onboardservice.exception;

public class WaitingApprovalException extends RuntimeException{

    public WaitingApprovalException(String message)
    {
        super(message);
    }
}
