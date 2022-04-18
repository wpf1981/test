package com.example.Ajax;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("数据处理")
public class Response {
    private boolean isok;
    private int code=200;
    private String message= "成功";
    private Object data;

    public Response(){}

    public static Response success(){
        Response response=new Response();
        response.setIsok(true);
        return response;
    }

    public static Response success(int code,String message){
        Response response=new Response();
        response.setIsok(true);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static Response success(Object obj,String message){
        Response response=new Response();
        response.setIsok(true);
        response.setMessage(message);
        response.setData(obj);
        return response;
    }

}
