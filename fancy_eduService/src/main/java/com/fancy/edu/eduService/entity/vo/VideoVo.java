package com.fancy.edu.eduService.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Joah
 * @time 2020/2/9 21:31
 */
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean isFree;
}
