package com.fancy.edu.eduService.service;

import com.fancy.edu.commonUtil.result.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Joah
 * @time 2020/1/31 21:54
 */
public interface OSSFileService {
    /**
     * 文件上传
     * @param file
     * @return
     */
    Result upload(MultipartFile file);
}
