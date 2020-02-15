package com.fancy.edu.commonUtil.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceException extends RuntimeException {

    private Integer code;

    private String message;

}
