package com.example.Controller;

import com.example.Ajax.Response;
import com.example.Entity.Files;
import com.example.Repository.FilesRepostitory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RequestMapping("/t2")
@Api(tags = "上传接口")
@RestController
@CrossOrigin
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

    @GetMapping("/Page3")
    @ApiOperation("自定义分页")
    public Response filePage(Files files){

        Map map=new HashMap();
        map.put("page",files.getPage());
        map.put("limit",files.getLimit());

        if(files.getUserId()==null||files.getUserId().trim().isEmpty()) {
            map.put("count", filesRepostitory.countPage());
            map.put("listData", filesRepostitory.findPage(files.getLimit(), files.getPage()));
        }else{
            map.put("count", filesRepostitory.userCountPage(files.getUserId()));
            map.put("listData", filesRepostitory.userFindPage(files.getLimit(), files.getPage(),files.getUserId()));
        }
        //
//       List<Files> files1;
//        files1=filesRepostitory.findPage(files.getLimit(),files.getPage());
//        log.info(""+files1.stream().count());
        //        log.info(""+map.get("listData"));
        return Response.success(map,"查询完成！");
    }

//    @ResponseBody
    @GetMapping("/list3")
    @ApiOperation("分页3")
    public Response filePage2(@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                       @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                       @RequestParam(name = "sort", defaultValue = "fileId", required = false) String sort) {
        PageRequest of = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, sort);
        Page<Files> page=filesRepostitory.findAll(of);
        return Response.success(page,"查询完成！");
    }

    @GetMapping("/listpage")
    @ApiOperation("分页2")
    public Response filePage3(Files files){
        if (files.getPage() == null){
            files.setPage(1L);
        }
        PageRequest of = PageRequest.of(files.getPage().intValue() - 1,6,Sort.Direction.DESC,"fileId");
        Page<Files> list = filesRepostitory.findAll(of);
        log.info("pageNum==" + list);
        return Response.success(list,"ok");
    }

    @GetMapping("/filePage")
    @ApiOperation("分页4")
    public Response filePage4(Files files){
        if (files.getPage() == null){
            files.setPage(1L);
        }
        int page=files.getPage().intValue() - 1;
        int limit=files.getLimit().intValue();
        Pageable of=PageRequest.of(page,limit);
//        PageRequest of = PageRequest.of(files.getPage().intValue() - 1,files.getLimit().intValue(),Sort.Direction.DESC,"file_id");
        Page<Files> list;
        log.info("userid::"+files.getUserId());
        if(files.getUserId()==null||files.getUserId().trim().isEmpty()) {
//            log.info("----------");
            list = filesRepostitory.findAll(of);
        }else{
//            log.info("+++++++++++");
            list = filesRepostitory.findFilename(files.getUserId(), of);
        }
//        log.info("page::" + list.getContent());
        Map map=new HashMap();
        map.put("page",files.getPage());
        map.put("limit",files.getLimit());
        map.put("count", list.getTotalElements());
        map.put("listData",list.getContent());
        return Response.success(map,"ok");
    }

    @Value("${myConfig.fileUrl}")
    private String FILE_URL;

    @GetMapping("/down")
    ResponseEntity download(String fileName,HttpServletRequest req) throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Files ff = filesRepostitory.findByFileName(fileName);
//        String URL="http://" + req.getServerName() + ":" + req.getServerPort() +"/files/"+dateFormat.format(ff.getCt());
        String URL="d:/upload/"+dateFormat.format(ff.getCt());
        log.info(URL);
//        URL= URLEncoder.encode(URL,"UTF-8");
        File dest=new File(URL,fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,"application/octet-stream")
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+fileName)
                .body(new FileSystemResource(dest));
    }
}
