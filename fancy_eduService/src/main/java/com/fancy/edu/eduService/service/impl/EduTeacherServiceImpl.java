package com.fancy.edu.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fancy.edu.eduService.entity.EduTeacher;
import com.fancy.edu.eduService.entity.vo.QueryTeacherVo;
import com.fancy.edu.eduService.mapper.EduTeacherMapper;
import com.fancy.edu.eduService.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Joah
 * @since 2020-01-29
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {


    /**
     *
     * 条件查询，带分页
     * @param eduTeacherPage
     * @param teacherVo
     * @return
     */
    @Override
    public IPage<EduTeacher> getMoreConditionPageList(Page<EduTeacher> eduTeacherPage, QueryTeacherVo teacherVo) {

        IPage<EduTeacher> eduTeacherIPage;
        // 判断teacherVo传过来的值是否为空
        if (ObjectUtils.isEmpty(teacherVo)){
            // 如果为空，直接查分页
            eduTeacherIPage = baseMapper.selectPage(eduTeacherPage,null);
        }else {
            // 不为空的时候,先取到所有的值，再判断
            String name = teacherVo.getName();
            String level = teacherVo.getLevel();
            String beginTime = teacherVo.getBeginTime();
            String endTime = teacherVo.getEndTime();
            QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
            if (!StringUtils.isEmpty(name)){
                // 拼接条件
                wrapper.like("name",name);
            }
            if (!StringUtils.isEmpty(level)){
                // 拼接条件
                wrapper.like("level",level);
            }
            if (!StringUtils.isEmpty(beginTime)){
                // 拼接条件
                wrapper.ge("gmt_create",beginTime);
            }
            if (!StringUtils.isEmpty(endTime)){
                // 拼接条件
                wrapper.le("gmt_create",endTime);
            }
            // 分页查询，带条件
            eduTeacherIPage = baseMapper.selectPage(eduTeacherPage, wrapper);
        }

        return eduTeacherIPage;
    }
}
