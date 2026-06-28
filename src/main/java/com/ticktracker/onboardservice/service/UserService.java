package com.ticktracker.onboardservice.service;

import com.ticktracker.onboardservice.enums.Status;
import com.ticktracker.onboardservice.model.User;
import com.ticktracker.onboardservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getUsers(String status , Pageable page)
    {
        if(status==null)
             return userRepository.findAll(page);
        else
            return  userRepository.findByStatus(Status.valueOf(status),page);

    }
}
