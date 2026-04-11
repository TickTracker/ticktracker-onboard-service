package com.ticktracker.onboardservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponseDTO {

    private String accessToken;
    private String refreshToken;
}
