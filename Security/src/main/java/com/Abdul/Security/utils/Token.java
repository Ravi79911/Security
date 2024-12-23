package com.Abdul.Security.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Token
{
    public String generateToken()
    {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }

//    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {
//
//        LocalDateTime now = LocalDateTime.now();
//        Duration diff = Duration.between(tokenCreationDate, now);
//
//        return diff.toMinutes() >=EXPIRE_TOKEN;
//    }


}
