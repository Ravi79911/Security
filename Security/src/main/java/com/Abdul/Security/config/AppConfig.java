package com.Abdul.Security.config;

import com.Abdul.Security.Dtos.UserDto;
import com.Abdul.Security.Repo.UserRepo;
import com.Abdul.Security.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@Configuration
public class AppConfig
{


    @Autowired
    private UserRepo userRepo;
    @Bean
  public PasswordEncoder passwordEncoder()
  {
      return new BCryptPasswordEncoder();
  }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
          return builder.getAuthenticationManager();
   }

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }



}
