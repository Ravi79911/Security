package com.Abdul.Security.controllers;

import com.Abdul.Security.entities.JwtRequest;
import com.Abdul.Security.entities.JwtResponse;
import com.Abdul.Security.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController
{
       @Autowired
       private UserDetailsService userDetailsService;

       @Autowired
       private AuthenticationManager manager;

       @Autowired
       private JwtHelper jwtHelper;
       @PostMapping(value ="/login")
       public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request)
       {
           this.doAuthenticate(request.getEmail(), request.getPassword()); // used authenticate
           UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
           if(userDetails.isEnabled())
           {
               String token = this.jwtHelper.generateToken(userDetails);
               JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();
               return new ResponseEntity<>(response, HttpStatus.OK);
           }
           return null;
       }



    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
}
