package com.restaurant.restaurant.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

public class JwtUtils {

    SecretKey key;

    public JwtUtils(String secret){
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String getToken(Long userId, String name){
        return Jwts.builder()
                .claim("userId", userId)
                .claim("name", name)
                .signWith(key)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}
