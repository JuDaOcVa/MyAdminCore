package com.judaocva.myadmincore.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final Algorithm algorithm;

    private final long expirationTime;

    private final long expirationRefreshToken;

    public JwtUtil(@Value("${auth.jwt.secret}") String secretKey,
                   @Value("${auth.jwt.expiration}") long expirationTime,
                   @Value("${auth.jwt.expiration-refresh}") long expirationRefreshToken) {
        this.algorithm = Algorithm.HMAC256(secretKey);
        this.expirationTime = expirationTime;
        this.expirationRefreshToken = expirationRefreshToken;
    }

    public String generateToken(String identificationCard) {
        return JWT.create()
                .withSubject(identificationCard)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(algorithm);
    }

    public String generateRefreshToken(String identificationCard) {
        return JWT.create()
                .withSubject(identificationCard)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationRefreshToken))
                .sign(algorithm);
    }

    public String validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public String validateRefreshToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}