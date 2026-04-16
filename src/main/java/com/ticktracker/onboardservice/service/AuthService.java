package com.ticktracker.onboardservice.service;

import com.ticktracker.onboardservice.dto.LoginRequestDTO;
import com.ticktracker.onboardservice.dto.LoginResponseDTO;
import com.ticktracker.onboardservice.dto.UserRegistrationDTO;
import com.ticktracker.onboardservice.enums.Role;
import com.ticktracker.onboardservice.enums.Status;
import com.ticktracker.onboardservice.exception.AdminAlreadyExistsException;
import com.ticktracker.onboardservice.exception.UserAlreadyExistsException;
import com.ticktracker.onboardservice.exception.WaitingApprovalException;
import com.ticktracker.onboardservice.jwtutil.JwtService;
import com.ticktracker.onboardservice.model.RefreshToken;
import com.ticktracker.onboardservice.model.User;
import com.ticktracker.onboardservice.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtService jwtService;

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


    public LoginResponseDTO login(LoginRequestDTO dto)
    {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getEmail() , dto.getPassword());

         if(authenticationManager.authenticate(token).isAuthenticated())
         {

             //TODO : Block login for the users with status pending

             User existingUser = userRepository.findByEmail(dto.getEmail());
             if(existingUser.getStatus().equals(Status.PENDING))
             {
                 throw new WaitingApprovalException("Your Onboarding is Pending From Admin");
             }
             String accessToken = jwtService.generateToken(existingUser);
             String refreshToken = refreshTokenService.generateRefreshToken(existingUser).getToken();

             return new LoginResponseDTO(accessToken,refreshToken);
         }

        // Throw Exception automatically if fails so no need to throw
        return null;

    }

    public String getAccessToken(String refreshToken)
    {
        RefreshToken token = refreshTokenService.getrefreshTokenByToken(refreshToken);
        return jwtService.generateToken(token.getUser());

    }

    public void logoutUser(Long id)
    {
        refreshTokenService.deleteRefreshTokenByUserId(id);
        return;
    }
}
