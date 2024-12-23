package com.Abdul.Security.config;


import com.Abdul.Security.security.JwtAuthenticationEntryPoint;
import com.Abdul.Security.security.JwtAuthenticationFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig
{
    @Autowired
     private JwtAuthenticationEntryPoint point;

    @Autowired
    private JwtAuthenticationFilter filter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
             http.csrf(csrf->csrf.disable()).cors(cors->cors.disable()).authorizeHttpRequests(
                     auth->auth.requestMatchers(
                             "/api/signUpUser",
                             "/auth/login",
                             "/api/forgot-password",
                             "/api/verify-account/**",
                             "/api/reset-password").permitAll().anyRequest().authenticated())
                     .exceptionHandling(ex->ex.authenticationEntryPoint(point))
                     .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                    http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
                    return http.build();
      }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }




}
