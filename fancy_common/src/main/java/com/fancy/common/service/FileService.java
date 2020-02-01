package com.fancy.common.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Joah
 * @time 2020/1/31 17:05
 */
public interface FileService {
    /**
     * 文件上传
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
