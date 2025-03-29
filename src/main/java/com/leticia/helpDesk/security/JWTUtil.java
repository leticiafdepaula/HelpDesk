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
        byte[] secretBytes = secret.getBytes();

        // Garante que a chave tenha pelo menos 512 bits (64 bytes) para HS512
        if (secretBytes.length < 64) {
            secretBytes = java.util.Arrays.copyOf(secretBytes, 64);  // Expande a chave, se necessário
        }
        SecretKey secretKey = Keys.hmacShaKeyFor(secretBytes);  // Criação da chave segura para assinatura

        // Gerar o token
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey, SignatureAlgorithm.HS512)  // Usando HS512
                .compact();
    }

    public String encodeData(String data) {
        return java.util.Base64.getUrlEncoder().encodeToString(data.getBytes());  // Codificação Base64 URL segura
    }
}
