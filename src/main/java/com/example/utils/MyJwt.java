package com.example.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Slf4j
public class MyJwt {

    static   String secret = "99c2918fe19d30bce25abfac8a3733ec";
    static final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());//StandardCharsets.UTF_8

//    static  long time = 1000 * 60 * 60 * 24;
    public static String createJwt(Long mins,Map<String,Object> payLoad) {
        long time = 1000*60*mins;
        Date ct = new Date(System.currentTimeMillis() + time);
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("slg", "HS256")
                //payload
                .setClaims(payLoad)
                .setSubject("admin-test")
                .setExpiration(ct)
//                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        log.info("当前时间："+String.valueOf(new Date(System.currentTimeMillis())));
        log.info("到期时间："+String.valueOf(ct));

        return jwtToken;
    }

    public  static Boolean parseJwt(String token){
        if(token==null){
            return false;
        }
        log.info(String.valueOf(new Date(System.currentTimeMillis())));
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key).build()
                    .parseClaimsJws(token);
        }catch (JwtException e){
            return false;
        }
        return true;
    }
}
