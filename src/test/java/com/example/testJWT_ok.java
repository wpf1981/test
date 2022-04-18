package com.example;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/*
eyJ0eXAiOiJKV1QiLCJzbGciOiJIUzI1NiIsImFsZyI6IkhTMjU2In0.
eyJ1c2VybmFtZSI6InRvbSIsInBhc3N3b3JkIjoicGFzcyIsInN1YiI6
ImFkbWluLXRlc3QiLCJleHAiOjE2NTAzNjMxMzN9.sGn1KujVXSeSQdg
qUL-39FvWaeau3kIG5fNYOnoneG4
 */

@Slf4j

public class testJWT_ok {

    @Test
    public void main() {

        long time = 1000 * 60 * 60 * 24;
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        JwtBuilder jwtBuilder = Jwts.builder();
        String secret="99c2918fe19d30bce25abfac8a3733ec";
        final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("slg", "HS256")
                //payload
                .claim("username", "tom")
                .claim("password", "pass")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + time))
//                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
        log.info(jwtToken);
        log.info(String.valueOf(Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(jwtToken).getBody().get("username")));

    }

    @Test
    public void parse() {
        String secret="99c2918fe19d30bce25abfac8a3733ec";
        final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        String token = "eyJ0eXAiOiJKV1QiLCJzbGciOiJIUzI1NiIsImFsZyI6IkhTMjU2In0.eyJ1c2VybmFtZSI6InRvbSIsInBhc3N3b3JkIjoicGFzcyIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2NTAzNjQ2MTh9.lYSdEnekOVn7q_M9DFD_WnK-of61IvFjLvvFnbfBglY";
        log.info(String.valueOf(Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token).getBody().get("username")));
    }

}
