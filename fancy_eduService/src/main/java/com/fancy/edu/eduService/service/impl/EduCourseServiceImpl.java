package com.fancy.edu.eduService.service.impl;

import com.fancy.edu.eduService.entity.EduCourse;
import com.fancy.edu.eduService.entity.EduCourseDescription;
import com.fancy.edu.eduService.entity.vo.CourseInfoVo;
import com.fancy.edu.eduService.handler.ServiceException;
import com.fancy.edu.eduService.mapper.EduCourseMapper;
import com.fancy.edu.eduService.service.EduCourseDescriptionService;
import com.fancy.edu.eduService.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Joah
 * @since 2020-02-07
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    /**
     * 课程描述
     */

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    /**
     * 添加课程信息
     * @param courseInfoVo
     * @return
     */
    @Transactional
    @Override
    public String insertCourseInfo(CourseInfoVo courseInfoVo) {
        // 添加课程的基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);

        int result = baseMapper.insert(eduCourse);
        // 判断课程信息是否添加成功   失败，抛出异常
        if (result == 0){
            throw new ServiceException(20001,"添加课程信息失败");
        }
        // 添加课程描述
        EduCourseDescription courseDescription = new EduCourseDescription();
        // 获取描述信息
        courseDescription.setDescription(courseInfoVo.getDescription());
        // 课程ID
        courseDescription.setId(eduCourse.getId());

        boolean save = eduCourseDescriptionService.save(courseDescription);

        return save ? eduCourse.getId() : null;
    }

    /**
     * 根据课程ID查询课程信息
     * @param id
     * @return
     */
    @Override
    public CourseInfoVo getCourseInfoById(String id) {
        // 查询两张表
        // 1、根据id查询课程表
        EduCourse eduCourse = baseMapper.selectById(id);
        if (ObjectUtils.isEmpty(eduCourse)){
            throw new ServiceException(20001,"该课程信息为空");
        }
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        // 课程基本信息
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        // 2、根据id查询课程描述表
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(id);
        courseInfoVo.setDescription(courseDescription.getDescription());
        // 返回课程信息
        return courseInfoVo;
    }
}
