package com.hivecrm.auth.util;

import com.hivecrm.auth.domain.member.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key SECRET;
    private final long EXPIRATION = 86400000; // 1 day in ms

    public JwtUtil() {
        try {
            SECRET = KeyGenerator
                            .getInstance(SignatureAlgorithm.HS256.getJcaName())
                            .generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SECRET, SignatureAlgorithm.HS256)
                .compact();
    }
}
