package com.ticktracker.onboardservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlatformSettings {

    @Id
    private long id;
    private boolean approvalRequired=false;
    private boolean registrationEnabled=true;
    private boolean maintainanceMode=false;
}
