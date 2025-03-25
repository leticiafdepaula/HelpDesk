package com.leticia.helpDesk.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

@Component
public class JWTUtils {

    @Value("${jwt.expiration}")
    private long expiration;

    public String genereteToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(null)
    }
}
