package com.ticktracker.onboardservice.service;

import com.ticktracker.onboardservice.dto.UserRegistrationDTO;
import com.ticktracker.onboardservice.enums.Role;
import com.ticktracker.onboardservice.enums.Status;
import com.ticktracker.onboardservice.exception.AdminAlreadyExistsException;
import com.ticktracker.onboardservice.exception.UserAlreadyExistsException;
import com.ticktracker.onboardservice.model.User;
import com.ticktracker.onboardservice.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationDTO dto)
    {
       User existingUser =  userRepository.findByEmail(dto.getEmail());
       if(existingUser != null)
       {
           throw  new UserAlreadyExistsException("User with email "+ dto.getEmail() + " already exists");
       }

        // Here existingUser is null , so Providing User.class.
        existingUser = modelMapper.map(dto,User.class);
        existingUser.setCreatedAt(new Date());
        existingUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        existingUser.setStatus(Status.ACTIVE);

       //TODO replce with userRepository.exists()   <S extends T> boolean exists(Example<S> example);
        // TODO Checks whether the data store contains elements that match the given Example.
        // TODO Params:
        //TODO example – the Example to use for the existence check. Must not be null.
        //TODO Returns:
        //TODO true if the data store contains elements that match the given Example.

        //If it's the first user of the platform , Assign ADMIN role and set Status as ACTIVE
        long count = userRepository.count();
        System.out.println(count+"wertyuio");
       if(count>0)
       {
           if(dto.getRole().equalsIgnoreCase("ADMIN"))
           {
               throw new AdminAlreadyExistsException("Sorry You Cant be an Admin");
           }
           //Not The first User
           existingUser.setStatus(Status.PENDING);
       }
       return userRepository.save(existingUser);
    }
}
