package com.example;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.security.Key;

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


    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws = Jwts.builder().setSubject("NB").signWith(key).compact();
        System.out.println(jws);
        System.out.println(
                Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody().getSubject().equals("NB")
        );

    }

    @Test
    public void t1(){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jwts = Jwts.builder().setSubject("Joe").signWith(key).compact();
        log.info(jwts);
        Key ekey=Keys.secretKeyFor(SignatureAlgorithm.HS384);
        try {

            Jwts.parserBuilder().setSigningKey(ekey).build().parseClaimsJws(jwts).getBody().getSubject().equals("NB");
            log.info(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwts).getBody().getSubject());
            log.info("++++++");
            //OK, we can trust this JWT

        } catch (JwtException e) {
            log.info("------");
            //don't trust the JWT!
        }
    }

}
