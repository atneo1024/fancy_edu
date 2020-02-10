package com.fancy.edu.eduService.mapper;

import com.fancy.edu.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fancy.edu.eduService.entity.vo.CourseDescVo;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Joah
 * @since 2020-02-07
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     * 根据课程ID获取课程信息
     * @param courseId
     * @return
     */
    CourseDescVo getCourseDesc(@RequestParam("courseId") String courseId);
}
