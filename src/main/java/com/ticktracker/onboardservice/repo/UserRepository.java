package com.ticktracker.onboardservice.repo;

import com.ticktracker.onboardservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

     User findByEmail(String email);
}
