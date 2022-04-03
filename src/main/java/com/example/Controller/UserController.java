package com.example.Controller;

import com.example.Ajax.Response;
import com.example.Entity.User;
import com.example.Repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
        return Response.success(userRepository.findById(id),"获取一条数据！");
    }
    @DeleteMapping("/user/{id}")
    @ApiOperation("删除一条数据")
    public Response deleteUser(@PathVariable("id") String id){
        userRepository.deleteById(id);
        return Response.success();
    }
    @PutMapping("/user")
    @ApiOperation("更新数据")
    public Response updateUser(@RequestParam String u_id,
                               @RequestParam String u_name){
        User user=new User();
        user=userRepository.getById(u_id);
        user.setUserName(u_name);
        return Response.success(userRepository.save(user),"还可以吧！");
    }

    @PostMapping("/user")
    @ApiOperation("添加一条数据")
    public Response addUser(@RequestBody User user){

        return Response.success(userRepository.save(user),"还可以吧！");
    }

}
