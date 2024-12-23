package com.Abdul.Security.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
     private int userId;
     private String userName;
     private String email;
     private String password;
     private Boolean isActive;
     private String otp;
     private LocalDateTime otpGeneratedTime;
     private String token;
     private LocalDateTime tokenCreationTime;
}
