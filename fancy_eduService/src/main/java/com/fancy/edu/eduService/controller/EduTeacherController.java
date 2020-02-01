package com.fancy.edu.eduService.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fancy.edu.commonUtil.result.Result;
import com.fancy.edu.eduService.entity.EduTeacher;
import com.fancy.edu.eduService.entity.vo.QueryTeacherVo;
import com.fancy.edu.eduService.handler.ServiceException;
import com.fancy.edu.eduService.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Joah
 * @since 2020-01-29
 */
@CrossOrigin
@RestController
@RequestMapping("/eduService/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    /**
     * 模拟获取用户信息
     * @return
     */
    @GetMapping("info")
    public Result getUserInfo(){

        return Result.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
    /**
     * 模拟登录
     * @return
     */
    @PostMapping("login")
    public Result login(){

        return Result.ok().data("token","admin");
    }

    /**
     * 8、异常处理
     * @return
     */
    @PostMapping("error")
    public Result error(){

        try {
            int i = 9 / 0;
        } catch (Exception e) {
            // 抛出自定义异常
            throw new ServiceException(20001,"你有点儿调皮");
        }

        return Result.ok();
    }

    /**
     * 7、根据ID更新教师信息
     * @param eduTeacher 更新的教师信息
     * @return
     */
    @PostMapping("updateTeacher")
    public Result updateTeacher( @RequestBody EduTeacher eduTeacher){

        boolean b = teacherService.updateById(eduTeacher);
        if (b){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    /**
     * 6、根据ID查询教师信息
     * @param id
     * @return
     */
    @GetMapping("getTeacherInfoById/{id}")
    public Result getTeacherInfoById(@PathVariable String id){

        EduTeacher eduTeacher = teacherService.getById(id);

        return Result.ok().data("eduTeacher", eduTeacher);
    }

    /**
     * 5、添加教师
     * @param teacher
     * @return
     */
    @PostMapping("addTeacher")
    public Result addTeacher( @RequestBody EduTeacher teacher ){

        boolean save = teacherService.save(teacher);

        if (save){
            return Result.ok();
        }else {
            return Result.error();
        }

    }

    /**
     *
     * 4、多条件组合查询分页
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("moreConditionPageList/{page}/{limit}")
    public Result getMoreConditionPageList(@PathVariable Long page, @PathVariable Long limit,
                                           @RequestBody(required = false) QueryTeacherVo teacherVo ){
        // 创建page对象，传递两个参数
        Page<EduTeacher> eduTeacherPage = new Page<>(page,limit);

        // 调用service的方法实现分页  、 组合条件查询
        IPage<EduTeacher> moreConditionPageList = teacherService.getMoreConditionPageList(eduTeacherPage, teacherVo);

        return Result.ok().data("page",moreConditionPageList);
    }

    /**
     *
     * 3、分页查询讲师列表
     * @return
     */
    @GetMapping("pageList/{page}/{limit}")
    public Result getAllTeacherInfo(@PathVariable Long page,
                                    @PathVariable Long limit){
        // 创建page对象，传递两个参数
        Page<EduTeacher> eduTeacherPage = new Page<>(page,limit);

        // 调用分页方法
        teacherService.page(eduTeacherPage,null);

        // 从eduTeacherPage 对象里面获取分页对象
        long total = eduTeacherPage.getTotal();

        List<EduTeacher> records = eduTeacherPage.getRecords();

        return Result.ok().data("total",total).data("records",records);
    }

    /**
     * 2、查询所有讲师信息
     * @return
     */
    @GetMapping
    public Result getAllTeacherInfo(){


        return Result.ok().data("items",teacherService.list(null));
    }

    /**
     * 1、逻辑删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable String id){

        return Result.ok().data("state",teacherService.removeById(id));
    }

}

