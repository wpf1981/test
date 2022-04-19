package com.example;

import com.example.utils.MyJwt;
import com.example.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class test {
    @Test
    void testjwt(){
        Map map = new HashMap();
        map.put("name","ok");
        log.info("token:"+com.example.utils.MyJwt.createJwt(1L,map));
    }

    @Test
    void testjwt2(){
        String jwt="eyJ0eXAiOiJKV1QiLCJzbGciOiJIUzI1NiIsImFsZyI6IkhTMjU2In0.eyJuYW1lIjoib2siLCJzdWIiOiJhZG1pbi10ZXN0IiwiZXhwIjoxNjUwMzQ1MDY5fQ.aIjj7E-crJTIdejF6Jo9sn83VNFB2qr2zUXpJI5Aw9M";
        log.info(MyJwt.parseJwt(jwt).toString());
    }

    @Test
    void testjwt3(){
        Map map = new HashMap();
        map.put("name","ok");
        log.info("token:"+JwtUtils.createJwt(map));
    }

    @Test
    void testjwt4(){
        String jwt="eyJ0eXAiOiJKV1QiLCJzbGciOiJIUzI1NiIsImFsZyI6IkhTMjU2In0.eyJuYW1lIjoib2siLCJpYXQiOjE2NTAzNDU0MTYsImV4cCI6MTY1MDM0NTQzNn0.kbeMB4STi0BToAfg55IV5YrHsHrBtou9Py2btTO9tp8";
        log.info(JwtUtils.parseJwt(jwt).toString());
    }
}
