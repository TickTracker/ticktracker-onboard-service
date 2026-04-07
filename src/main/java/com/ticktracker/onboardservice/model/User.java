package com.ticktracker.onboardservice.model;

import com.ticktracker.onboardservice.enums.Role;
import com.ticktracker.onboardservice.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false )
    private String name;
    @Column(unique = true , nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

}
