package com.example.Controller;

import com.example.Ajax.Response;
import com.example.Entity.Files;
import com.example.Repository.FilesRepostitory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RequestMapping("/t2")
@Api(tags = "上传接口")
@RestController
public class FileUploadController {
    @Resource
    FilesRepostitory filesRepostitory;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd/");

    @PostMapping("/upfile")
    @ApiOperation("上传图片")
    public Response upFile(@RequestParam(value = "File") MultipartFile file, HttpServletRequest req){
        Files files=new Files();
        String userid=req.getHeader("userId");
        // 得到上传时的文件名
        String originName = file.getOriginalFilename();
        if (!originName.endsWith(".pdf")) {
            log.info("上传失败：文件类型不对");
            return Response.success(203,"文件类型不对，必须为pdf！");
        }
        //设置路径名称
        String strFormat = simpleDateFormat.format(new Date());
        String uploadDir = "d:/upload/" + strFormat;
        File folder = new File(uploadDir);
        //创建文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }
        // 保存文件对象，加上uuid是为了防止文件重名
        //UUID.randomUUID()
        String strNewFileName = file.getOriginalFilename().replaceAll("-", "");
        try {
            log.info(""+!filesRepostitory.findByfileName(strNewFileName).isEmpty());
            int sid=filesRepostitory.findByfileName(strNewFileName).size()+1;
            if (!filesRepostitory.findByfileName(strNewFileName).isEmpty()) {
                strNewFileName = strNewFileName.replaceAll("\\.pdf", "_"+sid+".pdf");
            }
            log.info(strNewFileName);
            files.setFileName(strNewFileName);
            files.setCt(new Date(System.currentTimeMillis()));
            files.setStatus("Y");
            files.setUserId(userid);
            file.transferTo(new File(folder, strNewFileName));
            //返回下载链接
            String strUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() +"/files"+ strFormat + strNewFileName;
            files.setFileUrl(strUrl);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("上传失败："+e.getMessage());
            return Response.success(204,"error"+e.getMessage());
        }
        try{
            return Response.success(filesRepostitory.save(files),"上传成功！");
        }catch (Exception e){
            log.info("错误信息==>>"+e.getMessage());
            return Response.success(205,"重名文件！");
    }

    }

    @GetMapping("/getfile")
    @ApiOperation("查询图片")
    public Response getFile(){
        return Response.success(filesRepostitory.findAll(),"全部图片获取成功！");
    }

    @GetMapping("/ecode")
    @ApiOperation("响应代码")
    public String ecode(){
        List<String> ll= new ArrayList<>();
        ll.add("200:成功");
        ll.add("201:无此用户");
        ll.add("202:密码错误");
        ll.add("203:类型错误");
        ll.add("204:数据库异常");
        ll.add("205:重名文件");
        ll.add("206:成功");
        ll.add("207:成功");
        ll.add("208:成功");

        return String.valueOf(ll);
    }
}
