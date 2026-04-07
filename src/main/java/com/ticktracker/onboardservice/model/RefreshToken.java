package com.ticktracker.onboardservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false , unique = true )
    private String token;
    @OneToOne
    @JoinColumn(name = "user_id" , unique = true)
    private User user;
    @Column(nullable = false)
    private Date expiry;

}
