package com.ticktracker.onboardservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequestDTO {

    private String email;
    private String password;
}
