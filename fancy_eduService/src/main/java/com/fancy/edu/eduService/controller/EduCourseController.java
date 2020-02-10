package com.fancy.edu.eduService.controller;


import com.fancy.edu.commonUtil.result.Result;
import com.fancy.edu.eduService.entity.EduCourse;
import com.fancy.edu.eduService.entity.vo.CourseInfoVo;
import com.fancy.edu.eduService.service.EduCourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Joah
 * @since 2020-02-07
 */
@CrossOrigin
@RestController
@RequestMapping("/eduService/edu-course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    /**
     * 根据课程ID删除课程信息
     * @param id
     * @return
     */
    @DeleteMapping("/deleteCourse/{id}")
    public Result deleteCourse(@PathVariable String id){

        boolean flag = eduCourseService.removeCourseById(id);

        return flag ? Result.ok() : Result.error();
    }
    /**
     * 查询课程列表
     * @TODO 分页带条件
     */
    @GetMapping("/listCourse")
    public Result getCourseList(){
        List<EduCourse> list = eduCourseService.list(null);
        return Result.ok().data("items",list);
    }

    /**
     * 修改课程信息
     * @param courseInfoVo
     * @return
     */
    @PostMapping("/updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        boolean result = eduCourseService.updateCourseInfo(courseInfoVo);

        return result ? Result.ok() : Result.error();
    }

    /**
     * 添加课程信息
     * @param courseInfoVo
     * @return
     */
    @PostMapping("/addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        String courseId = eduCourseService.insertCourseInfo(courseInfoVo);

        return StringUtils.isBlank(courseId) ? Result.error() : Result.ok().data("courseId",courseId);
    }

    /**
     * 根据ID查询课程信息
     * @param id
     * @return
     */
    @GetMapping("/getCourseInfo/{id}")
    public Result getCourseInfoById(@PathVariable String id){

        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfoById(id);

        return Result.ok().data("courseInfo",courseInfoVo);
    }


}

