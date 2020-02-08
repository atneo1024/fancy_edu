package com.fancy.edu.eduService.service;

import com.fancy.edu.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fancy.edu.eduService.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Joah
 * @since 2020-02-07
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 添加课程信息
     * @param courseInfoVo
     * @return
     */
    String insertCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据ID 查询课程信息
     * @param id
     * @return
     */
    CourseInfoVo getCourseInfoById(String id);
}
