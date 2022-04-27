package com.example;

import com.example.Entity.Files;
import com.example.utils.JwtUtils;
import com.example.utils.MyJwt;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class test {
    @Test
    void testjwt(){
        Map map = new HashMap();
        map.put("name","ok");
        log.info("token:"+com.example.utils.MyJwt.createJwt(1L,map));
    }

    @Test
    void testjwt2(){
        String jwt="";
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
        String jwt="";
        log.info(JwtUtils.parseJwt(jwt).toString());
    }


    @Test
    void test(){
//        Page<Files> list = filesRepostitory.findList(1,10);
//        log.info(""+list);
//       List<Files> oo= filesRepostitory.findPage(5L,2L);
//        log.info(""+filesRepostitory.findPage(5L,2L));
        Map map=new HashMap();
        map.put("page",2L);
        map.put("limit",4L);
        Files files=new Files();
        files.setPage(1L);
        files.setLimit(5L);
        log.info(""+files);
    }

    public static void main(String[] args) {

        Date currentTime = new Date();

        System.out.println(currentTime); // 输出：Mon Feb 18 10:24:30 CST 2019

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy/MM/dd");



        System.out.println(simpleDateFormat1.format(currentTime));  // 输出2019-02-18 13:53:50.629

        System.out.println(simpleDateFormat2.format(currentTime));  // 输出2019-02-18

        System.out.println(simpleDateFormat3.format(currentTime));  // 输出2019/02/18
    }

}
