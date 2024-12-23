package com.Abdul.Security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(name ="userName",nullable = false,length = 255)
    private String userName;
    @Column(name ="email",nullable = false,length = 255)
    private String email;
    @Column(name ="password",nullable = false,length = 255)
    private String Password;
    @Column(name = "isActive")
    private Boolean isActive;
    @Column(name = "otp")
    private String otp;
    @Column(name = "otp-TimeStamp")
    private LocalDateTime otpGeneratedTime;
    @Column(name = "token")
    private String token;
    @Column(name= "token-TimeStamp")
    private LocalDateTime tokenCreationTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled()
    {

      return this.isActive;
    }


}
