package com.example;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Slf4j

public class testToken {

//    加密算法
//    HS256: HMAC using sun.security.provider.SHA-256
//    HS384: HMAC using SHA-384
//    HS512: HMAC using SHA-512
//
//    ES256: ECDSA using P-256 and SHA-256
//    ES384: ECDSA using P-384 and SHA-384
//    ES512: ECDSA using P-521 and SHA-512
//
//    RS256: RSASSA-PKCS-v1_5 using SHA-256
//    RS384: RSASSA-PKCS-v1_5 using SHA-384
//    RS512: RSASSA-PKCS-v1_5 using SHA-512
//    PS256: RSASSA-PSS using SHA-256 and MGF1 with SHA-256
//    PS384: RSASSA-PSS using SHA-384 and MGF1 with SHA-384
//    PS512: RSASSA-PSS using SHA-512 and MGF1 with SHA-512
    static final String secret="99c2918fe19d30bce25abfac8a3733ec";
    static SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

    public static void main(String[] args) {

        String jws = Jwts.builder().setSubject("中国").signWith(key,SignatureAlgorithm.HS256).compact();
        System.out.println(jws);
        System.out.println(
                Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody().getSubject().toString()
        );
    }

    @Test
    public void t1(){
       String jwts="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJOQiJ9.y5SRUxg9i_dAJtXBFv6_bowwcgNbWP-XZ-mIMaV_nSw";
        try {
            log.info(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwts).getBody().getSubject());
            log.info("解析成功！");
        } catch (JwtException e) {
            log.info("解析失败！");
        }
    }

}
