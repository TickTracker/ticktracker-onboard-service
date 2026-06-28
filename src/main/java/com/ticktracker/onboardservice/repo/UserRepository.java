package com.ticktracker.onboardservice.repo;

import com.ticktracker.onboardservice.enums.Status;
import com.ticktracker.onboardservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

     User findByEmail(String email);
     Page<User> findByStatus(Status status , Pageable page);
     Page<User> findAll(Pageable page);
}
