package com.fancy.edu.eduService.service;

import com.fancy.edu.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fancy.edu.eduService.entity.vo.CourseDescVo;
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

    /**
     * 修改课程信息
     * @param courseInfoVo
     * @return
     */
    boolean updateCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据ID删除课程信息
     * @param id
     * @return
     */
    boolean removeCourseById(String id);

    /**
     * 根据课程ID查询课程详情
     * @param courseId
     * @return
     */
    CourseDescVo getAllCourseInfo(String courseId);
}
