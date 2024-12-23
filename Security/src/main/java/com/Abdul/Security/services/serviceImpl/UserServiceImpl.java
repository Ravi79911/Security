package com.Abdul.Security.services.serviceImpl;

import com.Abdul.Security.Dtos.ForgotPassword;
import com.Abdul.Security.Dtos.UserDto;
import com.Abdul.Security.Repo.UserRepo;
import com.Abdul.Security.entities.User;
import com.Abdul.Security.exception.EmailNotFoundException;
import com.Abdul.Security.exception.ResourceNotFoundException;
import com.Abdul.Security.services.UserService;
import com.Abdul.Security.utils.EmailUtils;
import com.Abdul.Security.utils.Otp;
import com.Abdul.Security.utils.Token;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    private static final long EXPIRE_TOKEN = 30;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Otp otp;

    @Autowired
    private Token token;
    @Autowired
    private EmailUtils emailUtils;
    @Override
    public UserDto signUpUser(UserDto userDto)  {
        String existingEmail = userDto.getEmail();
        if (userRepo.existsByEmail(existingEmail)) // to check mail already exist or not
        {
            throw new EmailNotFoundException("email already exist with mail id : " + userDto.getEmail()); // throwing exception email already exist
        }
        String userOtp = otp.generateOtp(); // getting otp
           try {
                  emailUtils.sendOptToEmail(existingEmail, userOtp);
             }
           catch (MessagingException messagingException)
           {
                throw new RuntimeException("Unable to send Otp please try again later");
           }

        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setOtp(userOtp);
        user.setOtpGeneratedTime(LocalDateTime.now());
//        user.setUserName(userDto.getUserName());
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public String verifyAccount(String email,String otp)
    {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new EmailNotFoundException("email not found with email Id" + email));
        if(user.getOtp().equals(otp)&& Duration.between(user.getOtpGeneratedTime(),LocalDateTime.now()).getSeconds()<5*60)
        {

                   user.setIsActive(true);
                   userRepo.save(user);
                   return "user verified successfully";
        }

             return "please regenerated Otp and try again";
    }

    @Override
    public String forgotPassword(String email)
    {
         User user = userRepo.findByEmail(email).orElseThrow(() -> new EmailNotFoundException("email not found" + email));
        String generateToken = token.generateToken();
        try
                      {
                          emailUtils.sendSetpasswordEmail(email,generateToken);
                       }
                    catch (MessagingException messagingException)
                         {
                             throw new RuntimeException("Unable to send link please try again later");
                         }

                      user.setToken(generateToken);
                      user.setTokenCreationTime(LocalDateTime.now());
                      userRepo.save(user);
        return "please check your mail ,  link has been send to change your password";
    }

    @Override
    public String ResetPssword(String token,ForgotPassword forgotPassword)
    {
             if(forgotPassword.getNewPassword().equals(forgotPassword.getConfirmPassword()))
             {
                 User user = userRepo.findByToken(token).orElseThrow(() -> new EmailNotFoundException("email not found "));
                 if (user.getToken().equals(token) && Duration.between(user.getTokenCreationTime(), LocalDateTime.now()).getSeconds() < 5 * 60)
                 {
                            user.setPassword(passwordEncoder.encode(user.getPassword()));
                            userRepo.save(user);
                            return "your password has been changed successfully";
                 }
                 else {
                          return "your succession has been expires please try again";
                      }
             }

             else {
                    return "New password does not Match with Confirm Password";
                   }

    }


}
