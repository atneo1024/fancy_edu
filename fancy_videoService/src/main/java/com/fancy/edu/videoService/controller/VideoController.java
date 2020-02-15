package com.fancy.edu.videoService.controller;

import com.fancy.edu.commonUtil.result.Result;
import com.fancy.edu.videoService.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Joah
 * @time 2020/2/14 22:09
 */
@CrossOrigin
@RestController
@RequestMapping("/videoController/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    public Result uploadAliYunVideo(@RequestParam("file") MultipartFile file){

        /**
         * 调用方法实现视频上传，返回上传后的视频id
         */
        String videoId = videoService.uploadAliYunVideo(file);

        return Result.ok().data("videoId",videoId);
    }
}
