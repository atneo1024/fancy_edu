package com.fancy.edu.eduService.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fancy.edu.eduService.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fancy.edu.eduService.entity.vo.QueryTeacherVo;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Joah
 * @since 2020-01-29
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 条件查询，带分页
     * @param eduTeacherPage
     * @param teacherVo
     * @return
     */
    IPage<EduTeacher> getMoreConditionPageList(Page<EduTeacher> eduTeacherPage, QueryTeacherVo teacherVo);

}
