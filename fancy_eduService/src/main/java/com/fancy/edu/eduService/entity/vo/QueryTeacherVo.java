package com.fancy.edu.eduService.entity.vo;

import lombok.Data;

/**
 * 条件查询的Vo
 */
@Data
public class QueryTeacherVo {

    private String name;

    private String level;

    private String beginTime;

    private String endTime;
}
