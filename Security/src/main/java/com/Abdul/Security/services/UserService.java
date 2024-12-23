package com.Abdul.Security.services;

import com.Abdul.Security.Dtos.ForgotPassword;
import com.Abdul.Security.Dtos.UserDto;
import com.Abdul.Security.exception.EmailNotFoundException;
import com.Abdul.Security.utils.Token;
import jakarta.mail.MessagingException;

public interface UserService
{
    UserDto signUpUser(UserDto userDto) throws MessagingException;
    String verifyAccount(String email,String Otp);
    String forgotPassword(String email);

    String ResetPssword(String token, ForgotPassword  forgotPassword);

}
