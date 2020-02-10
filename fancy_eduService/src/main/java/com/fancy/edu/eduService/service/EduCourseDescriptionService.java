package com.fancy.edu.eduService.service;

import com.fancy.edu.eduService.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author Joah
 * @since 2020-02-07
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    /**
     * 根据课程ID删除课程描述
     * @param id
     * @return
     */
    boolean deleteDescByCourseId(String id);
}
