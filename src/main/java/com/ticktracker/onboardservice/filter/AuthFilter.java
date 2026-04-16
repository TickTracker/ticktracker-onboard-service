package com.ticktracker.onboardservice.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;


@Component
public class AuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String email = request.getHeader("X-USER-EMAIL");
        //No X-USER-EMAIL means request not coming from gateway ( no jwt validation done )
        if(email == null ||  email.isEmpty())
        {
            System.out.println("ROLE if");
             filterChain.doFilter(request,response);
        }
        //Means it has header , authenticated by gateway and set header , authentication context is not set
        //Set explicitly
       else if( SecurityContextHolder.getContext().getAuthentication() == null)
        {
            String role = request.getHeader("X-USER-ROLE");

            System.out.println("ROLE "+role);
            // Convert role header into Spring Security authority
            // Example: ADMIN -> ROLE_ADMIN
            SimpleGrantedAuthority authority =
                    new SimpleGrantedAuthority("ROLE_" + role);

            // Create Authentication object
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            email,                 // principal
                            null,                  // no password needed
                            Arrays.asList(authority)    // authorities
                    );

            // Store authentication in SecurityContext
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            //Adding the whole request in the security context , so that later
            // we can fetch additional details from request inside security context
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Continue request processing
            filterChain.doFilter(request, response);
        }
       else {
            System.out.println("ROLE else");
           filterChain.doFilter(request,response);
        }
    }
}
