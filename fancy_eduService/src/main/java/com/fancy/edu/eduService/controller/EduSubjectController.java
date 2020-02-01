package com.fancy.edu.eduService.controller;


import com.fancy.edu.commonUtil.result.Result;
import com.fancy.edu.eduService.entity.vo.SubjectNestedVo;
import com.fancy.edu.eduService.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Joah
 * @since 2020-02-01
 */
@CrossOrigin
@RestController
@RequestMapping("/eduService/edu-subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * 通过上传 Excel 文件获取文件内容
     * @return
     */
    @PostMapping("/import")
    public Result importExcelSubject(@RequestParam("file")MultipartFile file){

        // 1、获取上传的excel文件

        List<String> result = eduSubjectService.importExcelSubject(file);
        if (CollectionUtils.isEmpty(result)){
            return Result.ok();
        }else {
            return Result.error().message("部分数据导入失败").data("msg",result);
        }
    }

    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("/nestedList")
    public Result nestedList(){

        List<SubjectNestedVo> subjectNestedVoList = eduSubjectService.nestedList();
        return Result.ok().data("items", subjectNestedVoList);
    }

    /**
     * 删除课程
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Result deleteSubjectById(@PathVariable String id){
        boolean flag = eduSubjectService.deleteSubjectById(id);

        return flag ? Result.ok() : Result.error();
    }
}

