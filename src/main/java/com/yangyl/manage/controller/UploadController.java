package com.yangyl.manage.controller;

import com.yangyl.manage.common.dto.Response;
import com.yangyl.manage.entity.ImageInfo;
import com.yangyl.manage.service.ImageInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;


@Slf4j
@Api(value = "文件上传")
@Controller
@RequestMapping("/pictures")
public class UploadController {

    @Autowired
    private ImageInfoService imageInfoService;

    @ApiOperation(value = "图片上传接口")
    @PostMapping()
    @ResponseBody
    public Response upload(@RequestParam Integer type,
                           @RequestParam String userCode,
                           @RequestParam String fileName,
                           @RequestParam("file") MultipartFile file) {
        if (StringUtils.isEmpty(userCode)) {
            return Response.err("用户编码不能为null或者空");
        }
        if (StringUtils.isEmpty(fileName)) {
            return Response.err("文件名称不能为null或者空");
        }
        Response<ImageInfo> response =
                imageInfoService.uploadFile(file, type, userCode, fileName);
        if (response.getCode() == 200) {
            return Response.ok(String.valueOf(response.getData()));
        }
        return Response.err(response.getMsg());
    }

    @ApiOperation(value = "图片回显接口")
    @GetMapping("/{pictureCode}")
    public void download(@PathVariable String pictureCode, HttpServletResponse response) throws Exception {
        Response<ImageInfo> response1 = imageInfoService.queryByCode(pictureCode);
        if (!(response1.getCode() == 200)) {
            return;
        }
        ImageInfo picture = response1.getData();
        File file = new File(picture.getImgUrl());
        if (file.exists()) {
            response.setContentType("application/octet-stream");
            response.setContentLength((int) file.length());
            response.setHeader("Content-Disposition", "filename=" + picture.getFileName());
            FileInputStream inputStream = new FileInputStream(file);
            OutputStream out = response.getOutputStream();
            byte[] data = new byte[1024];
            int count = 0;
            while ((count = inputStream.read(data)) >= 0) {
                out.write(data, 0, count);
            }
            inputStream.close();
            out.close();
        }
    }

}
