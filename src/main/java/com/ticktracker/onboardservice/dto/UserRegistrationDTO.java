package com.ticktracker.onboardservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegistrationDTO {


    private String name;
    @NotEmpty(message = "Email Cannot Be Empty/Blank/Null")
    @Email(message = "Provide Actual EMail")
    private String email;
    @NotEmpty(message = "Password Cannot be Empty")
    private String password;
    private String role;

}
