package com.Abdul.Security.services.serviceImpl;
import com.Abdul.Security.Repo.UserRepo;
import com.Abdul.Security.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService  implements UserDetailsService
{

    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepo.findByEmail(username).orElseThrow(() -> new RuntimeException("user not found wiht username" + username));
        return  user;

    }



}
