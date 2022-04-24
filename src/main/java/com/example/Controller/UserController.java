package com.example.Controller;

import com.example.Ajax.Response;
import com.example.Entity.User;
import com.example.Repository.UserRepository;
import com.example.utils.MyJwt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/t2")
@Api(tags = "用户接口")
public class UserController {
    @Resource
    UserRepository userRepository;

    @GetMapping("/user")
    @ApiOperation("获取所有数据")
    public Response findUserList(){
        return Response.success(userRepository.findAll(),"所有数据！");
    }

    @GetMapping("/user/{id}")
    @ApiOperation("获取一条数据")
    public Response getUser(@PathVariable("id") String id){
        log.info(String.valueOf(userRepository.findById(id)));
        return Response.success(userRepository.getById(id),"获取一条数据！");
    }

    @DeleteMapping("/user/{id}")
    @ApiOperation("删除一条数据")
    public Response deleteUser(@PathVariable("id") String id){
        userRepository.deleteById(id);
        return Response.success(200,"ok");
    }
    @PutMapping("/user/{id}")
    @ApiOperation("更新数据")
    public Response updateUser(@PathVariable("id") String id,
//            @RequestParam String id,
                               @RequestBody User n_user){
        User user=new User();
        user=userRepository.getById(id);
        user.setUserName(n_user.getUserName());
        return Response.success(userRepository.save(user),"还可以吧！");
    }

    @PostMapping("/user")
    @ApiOperation("添加一条数据")
    public Response addUser(@RequestBody User user){

        return Response.success(userRepository.save(user),"还可以吧！");
    }

    @GetMapping("/login")
    @ApiOperation("用户登录")
    public Response loginUser(User user){
        Map<String, Object> map = new HashMap<>();
        map.put("userid",user.getUserId());
        map.put("username",user.getUserName());
        if(!userRepository.existsById(user.getUserId())){
            return Response.success(201,"无此用户！");
        }else {
            User userdata=new User();
            userdata=userRepository.getById(user.getUserId());
            if (user.getPassWord().equals(userdata.getPassWord())){
                userdata.setToken(MyJwt.createJwt(60L,map));
                userdata.setCt(String.valueOf(new Date(System.currentTimeMillis())));
//                MyJwt.
                log.info("获取token："+userdata.getToken());
                return Response.success(userRepository.save(userdata),"登录成功!");
            }else {
                return Response.success(202,"密码错误！");
            }
        }
    }

    @GetMapping("/checkToken")
    public Boolean checkToken(HttpServletRequest request){
        String token=request.getHeader("token");
        log.info("验证：" +token);
        log.info("==="+MyJwt.parseJwt(token));
        return MyJwt.parseJwt(token);
    }
}
