package com.example.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Slf4j
//@Component
public class JwtUtils {
    //    @Value("{jwt.skin.key}")
    //key的大小必须大于或等于256bit,需要32位英文字符，一个英文字符为：8bit,一个中文字符为12bit
    static String key="ssssssssssdfdsafasfdssdfsfsfssfs";
    //设置加密算法
    static SignatureAlgorithm signatureAlgorithm= SignatureAlgorithm.HS256;
    /**
     * 获取转换后的私钥对象
     * @return
     */
    static SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(key.getBytes());
    }
    static final SecretKey getSecretKey = Keys.hmacShaKeyFor(key.getBytes());//StandardCharsets.UTF_8
    /**
     * 生成JWT
     * @param exp 指定过期时间，不能小于当前时间
     * @param payLoad 携带的数据
     * @return
     */
    static long time = 1000 * 60 * 60 * 24;
//    static long time = 1000*20;


    public static String createJwt(Map<String,Object> payLoad){
        Date exp= new Date(System.currentTimeMillis() + time);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("slg", "HS256")
                .setClaims(payLoad)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(exp)
                .signWith(getSecretKey,signatureAlgorithm)
                .compact();
    }

    /**
     * 解析JWS，返回一个布尔结果
     * @param jwsString
     * @return
     */
    public  static Boolean parseJwt(String jwsString){
        if(jwsString==null){
            return false;
        }
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey)
                    .build()
                    .parseClaimsJws(jwsString);
        }catch (JwtException e){
           return false;
        }
        return true;
    }

    /**
     * 解析Jws,返回一个Jws对象
     * @param jwsString
     * @return
     */
    public  static Jws parseJwtResultJws(String jwsString){
        Jws<Claims> claims=null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(getSecretKey)
                    .build()
                    .parseClaimsJws(jwsString);
        }catch (JwtException e){
            e.printStackTrace();
        }
        return claims;
    }
    /**
     * 获取header中的数据
     * @param jwsString
     * @return
     */
    public static Map<String,Object> getHeader(String jwsString){
        return parseJwtResultJws(jwsString).getHeader();
    }

    /**
     * 获取PayLoad中携带的数据
     * @param jwsString
     * @return
     */
    public static Map<String,Object> getPayLoad(String jwsString){
        return ((Map<String, Object>) (parseJwtResultJws(jwsString)).getBody());
    }

    /**
     * 获取除去exp和iat的数据，exp：过期时间，iat：JWT生成的时间
     * @param jwsString
     * @return
     */
    public static Map<String,Object> getPayLoadALSOExcludeExpAndIat(String jwsString){
        Map<String, Object> map = getPayLoad(jwsString);
        map.remove("exp");
        map.remove("iat");
        return map;
    }

//    public static void main(String[] args) {
//        JwtUtils jwtUtils = new JwtUtils();
//        Date exp = new Date(System.currentTimeMillis()+60*60*24);
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("name","张三");
//        String jwt = jwtUtils.createJwt(exp, hashMap);
//        System.out.println(jwt);
//        System.out.println(jwtUtils.getPayLoadALSOExcludeExpAndIat(jwt));
//    }

}
