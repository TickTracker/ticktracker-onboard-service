package com.ticktracker.onboardservice.exception;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExceptionResponse {

    private String message;
    private HttpStatus status;
}
