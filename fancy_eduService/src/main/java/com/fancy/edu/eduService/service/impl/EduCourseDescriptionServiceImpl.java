package com.fancy.edu.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fancy.edu.eduService.entity.EduCourseDescription;
import com.fancy.edu.eduService.mapper.EduCourseDescriptionMapper;
import com.fancy.edu.eduService.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author Joah
 * @since 2020-02-07
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

    /**
     * 根据课程ID删除课程描述
     * @param id
     * @return
     */
    @Override
    public boolean deleteDescByCourseId(String id) {

        int delete = baseMapper.deleteById(id);
        return delete > 0;
    }
}
