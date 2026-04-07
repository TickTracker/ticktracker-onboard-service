package com.ticktracker.onboardservice.controller;

import com.ticktracker.onboardservice.dto.UserRegistrationDTO;
import com.ticktracker.onboardservice.model.User;
import com.ticktracker.onboardservice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User registerUser(@RequestBody @Valid UserRegistrationDTO dto)
    {
        return authService.registerUser(dto);

    }




}
