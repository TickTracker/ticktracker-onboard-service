package com.ticktracker.onboardservice.repo;

import com.ticktracker.onboardservice.model.RefreshToken;
import com.ticktracker.onboardservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken , Long> {

    public RefreshToken findByUser(User user);
    public RefreshToken findByToken(String token);
    public RefreshToken deleteByUser_Id(Long id);

}
