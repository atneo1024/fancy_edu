package com.fancy.edu.eduService.controller;

import com.fancy.edu.commonUtil.result.Result;
import com.fancy.edu.eduService.service.OSSFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 上传文件到oss
 * @author Joah
 * @time 2020/1/31 14:58
 */

@CrossOrigin
@RestController
@RequestMapping("/eduService/edu-file")
public class FileUploadController {

    @Autowired
    private OSSFileService ossFileService;

    @PostMapping("/uploadFile")
    public Result uploadTeacherImg(@RequestParam("file") MultipartFile file,String host) {

        return ossFileService.upload(file,host);
    }
}
