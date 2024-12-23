package com.Abdul.Security.controllers;

import com.Abdul.Security.Dtos.ForgotPassword;
import com.Abdul.Security.Dtos.UserDto;
import com.Abdul.Security.services.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeControllers
{
    @Autowired
    private UserService  userService;


    @PostMapping(value = "/signUpUser")
    public ResponseEntity<UserDto> signUpUser(@RequestBody UserDto  userDto) throws MessagingException {
        UserDto signedUpUser = userService.signUpUser(userDto);
        return new ResponseEntity<>(signedUpUser, HttpStatus.CREATED);
    }

    @PutMapping(value = "/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestParam String email,@RequestParam String otp)
    {
        String message = userService.verifyAccount(email, otp);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @PostMapping(value = "/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email)
    {
        String message = userService.forgotPassword(email);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
     @PutMapping(value = "/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody ForgotPassword forgotPassword)
    {
        String message = userService.ResetPssword(token, forgotPassword);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }





}
