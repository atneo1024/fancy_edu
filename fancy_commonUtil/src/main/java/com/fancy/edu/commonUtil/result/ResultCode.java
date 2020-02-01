package com.fancy.edu.commonUtil.result;

/**
 * 定义返回数据使用的状态码
 */
public interface ResultCode {

    /**
     * 成功
     */
    int SUCCESS = 20000;

    /**
     * 失败
     */
    int ERROR = 20001;

    /**
     * 没有访问权限
     */
    int AUTH = 30000;

    /**
     * 上传文件失败
     */
    int FILE_UPLOAD_ERROR = 30000;

}
