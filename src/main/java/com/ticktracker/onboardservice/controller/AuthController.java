package com.ticktracker.onboardservice.controller;

import com.ticktracker.onboardservice.dto.LoginRequestDTO;
import com.ticktracker.onboardservice.dto.LoginResponseDTO;
import com.ticktracker.onboardservice.dto.UserRegistrationDTO;
import com.ticktracker.onboardservice.model.RefreshToken;
import com.ticktracker.onboardservice.model.User;
import com.ticktracker.onboardservice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User registerUser(@RequestBody @Valid UserRegistrationDTO dto) {
        return authService.registerUser(dto);

    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO dto) {
        return authService.login(dto);
    }


    @GetMapping("/refresh/{refreshToken}")
    public String getAccessToken(@PathVariable("refreshToken") String refreshToken) {
        return authService.getAccessToken(refreshToken);
    }

    @PostMapping("/logout/{id}")
    public void logout(@PathVariable Long id)
    {
        authService.logoutUser(id);
    }



}
