package com.hivecrm.auth.util;

import com.hivecrm.auth.dto.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.PrivateKey;
import java.util.Date;

@Component
public class JwtUtil {
    private final PrivateKey secretKey;
    private static final long EXPIRATION = 86400000; // 1 day in ms

    public JwtUtil(PrivateKey secretKey) {
        this.secretKey = secretKey;
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(secretKey, SignatureAlgorithm.RS256)
                .compact();
    }
}
