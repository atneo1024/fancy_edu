package com.fancy.edu.videoService.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Joah
 * @time 2020/2/14 22:19
 */
public interface VideoService {

    /**
     * 上传视频
     * @param file
     * @return
     */
    String uploadAliYunVideo(MultipartFile file);
}
