package com.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtil {

    @Value("${jwt.issuer}")
    private String ISSUER;//project-name

    @Value("${jwt.audience}")
    private String AUDIENCE;//web

    @Value("${jwt.expires_in}")
    private int EXPIRES_IN;//30

    private String encryKey = "project-name";

    private Algorithm ALGORITHM = Algorithm.HMAC256(encryKey);

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static Boolean result = false;
    public static String message = "";

    public String generateToken(String username) {


        long currentTime = System.currentTimeMillis();
        logger.info("=====generateToken===now    is " + new Date(currentTime));
        logger.info("=====generateToken===expire is " + new Date(currentTime + EXPIRES_IN * 1000 * 60));

        String token = JWT.create()

                .withIssuer(ISSUER)

                .withIssuedAt(new Date(currentTime))// 签发时间

                .withExpiresAt(new Date(currentTime + EXPIRES_IN * 1000 * 60))// 过期时间戳

                .withClaim("username", username)//自定义参数

                .sign(ALGORITHM);

        return token;
    }

    public void verifyToken(String token) {

        try {

            if (null == token) {
                result = false;
                message = "token is null";
                return;
            }
            // Reusable verifier instance
            JWTVerifier verifier = JWT.require(ALGORITHM)

                    .withIssuer(ISSUER)

                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);

            // verify issuer
            String issuer = decodedJWT.getIssuer();

            // verity 自定义参数
            String username = decodedJWT.getClaim("username").asString();
            if (("").equals(username)) {
                result = false;
                message = "user is error";
                return;
            }

        } catch (TokenExpiredException e) {
            result = false;
            message = "token is expired";
            return;
        }
        result = true;
        message = "token is verified";

    }

}
