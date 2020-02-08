package com.fancy.edu.eduService.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Joah
 * @time 2020/1/31 21:32
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;
    /**
     * 存储 封面文件夹名字
     */
    @Value("${aliyun.oss.file.cover}")
    private String cover;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;
    /**
     * 存储 头像文件夹名字
     */
    @Value("${aliyun.oss.file.career}")
    private String career;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    public static String COVER;
    public static String CAREER;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
        COVER = cover;
        CAREER = career;
    }
}
