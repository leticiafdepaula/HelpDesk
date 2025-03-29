package com.leticia.helpDesk.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(String email) {
        // Garante que a chave tenha pelo menos 256 bits
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

        // Gerar o token
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey, SignatureAlgorithm.HS256)  // Usando HS256 ou outra assinatura
                .compact();
    }

    public String encodeData(String data) {
        return java.util.Base64.getUrlEncoder().encodeToString(data.getBytes());
    }
}