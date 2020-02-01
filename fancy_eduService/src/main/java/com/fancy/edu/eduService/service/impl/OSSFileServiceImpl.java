package com.fancy.edu.eduService.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.fancy.edu.commonUtil.result.Result;
import com.fancy.edu.eduService.handler.ConstantPropertiesUtil;
import com.fancy.edu.eduService.service.OSSFileService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Joah
 * @time 2020/1/31 21:54
 */
@Service
public class OSSFileServiceImpl implements OSSFileService {


    @Override
    public Result upload(MultipartFile file){

        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId,accessKeySecret);

        // 1、获取到上传的文件
        // 2、获取上传文件的文件名称
        String filename = file.getOriginalFilename();
        String string = UUID.randomUUID().toString();
        filename = string + filename;
        // 获取当前时间（按照时间分割文件夹）
        String filePath = new DateTime().toString("yyyy/MM/dd");
        filename = filePath + "/" + filename;
        try {
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(bucketName, filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            String path = "http://" + bucketName + "." + endpoint + "/" + filename;
            return Result.ok().data("path",path);
        } catch (IOException e) {
            return Result.error();
        }
    }
}
