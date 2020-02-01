package com.fancy.edu.eduService.handler;

import com.fancy.edu.commonUtil.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 1、统一异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error().message("服务加载中...");
    }

    /**
     * 2、自定义异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public Result error(ServiceException e){
        e.printStackTrace();
        return Result.error().message(e.getMessage()).code(e.getCode());
    }

}
