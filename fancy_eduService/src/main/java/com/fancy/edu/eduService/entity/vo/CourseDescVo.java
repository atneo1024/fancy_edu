package com.fancy.edu.eduService.entity.vo;

import lombok.Data;

/**
 * 课程详情
 * @author Joah
 * @time 2020/2/10 17:43
 */
@Data
public class CourseDescVo {

    private String id;

    private String title;

    private String cover;

    private String price;

    private String description;
    /**
     * 总课时数
     */
    private String lessonNum;
    /**
     * 讲师名称
     */
    private String teacherName;

    /**
     * 一级分类
     */
    private String levelOne;

    /**
     * 二级分类
     */
    private String levelTwo;


}
