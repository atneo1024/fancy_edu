package com.fancy.edu.commonUtil.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Joah
 * @time 2020/1/31 17:12
 */
@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    /**
     * 上传文件失败
     */
    FILE_UPLOAD_ERROR(false, 21004, "文件上传错误");

    private boolean state;
    private int code;
    private String msg;

}
