package com.ticktracker.onboardservice.service;

import com.ticktracker.onboardservice.exception.InvalidRefreshTokenException;
import com.ticktracker.onboardservice.exception.RefreshTokenExpiredException;
import com.ticktracker.onboardservice.model.RefreshToken;
import com.ticktracker.onboardservice.model.User;
import com.ticktracker.onboardservice.repo.RefreshTokenRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepo refreshTokenRepo;

    public RefreshToken generateRefreshToken(User existingUser) {

        RefreshToken existingToken = refreshTokenRepo.findByUser(existingUser);
        if(existingToken!=null)
        {
            //Delete Existing Token from Database , One session is allowed per user
            refreshTokenRepo.delete(existingToken);
        }

        String token = UUID.randomUUID().toString();
        //Refresh Token expiry is 7 days
        RefreshToken newToken = new RefreshToken(token, existingUser , new Date(System.currentTimeMillis()+60*60*1000*24*7));

        return refreshTokenRepo.save(newToken);
    }

    public RefreshToken getrefreshTokenByToken(String token)
    {
       RefreshToken existingToken =  refreshTokenRepo.findByToken(token);

       if(existingToken==null) // Means User Logged Out , Login again .
       {
           throw new InvalidRefreshTokenException("User Logged Out , Please login again");
       }

       //TODO also check if refresh token expired or not , if expired throw refresh token expired login again
        if(new Date().after(existingToken.getExpiry()))
        {
            throw new RefreshTokenExpiredException("RefreshToken expired. Please log in ");
        }

       return existingToken;
    }

    @Transactional
    public RefreshToken deleteRefreshTokenByUserId(long id)
    {
        return refreshTokenRepo.deleteByUser_Id(id);
    }



}
