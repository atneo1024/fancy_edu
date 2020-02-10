package com.fancy.edu.eduService.controller;


import com.fancy.edu.commonUtil.result.Result;
import com.fancy.edu.eduService.entity.EduChapter;
import com.fancy.edu.eduService.entity.vo.ChapterVo;
import com.fancy.edu.eduService.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Joah
 * @since 2020-02-09
 */
@CrossOrigin
@RestController
@RequestMapping("/eduService/edu-chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    /**
     * 删除章节信息
     * @param chapterId
     * @return
     */
    @DeleteMapping("/{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId){

        boolean result = eduChapterService.removeByChapterId(chapterId);

        return result ? Result.ok() : Result.error();
    }

    /**
     * 修改章节方法
     * @param chapter
     * @return
     */
    @PostMapping("/updateChapter")
    public Result updateChapter(@RequestBody EduChapter chapter){

        boolean b = eduChapterService.updateById(chapter);

        return b ? Result.ok() : Result.error();
    }

    /**
     * 根据章节ID查询章节信息
     * @param chapterId
     * @return
     */
    @GetMapping("/getChapterInfo/{chapterId}")
    public Result getChapterInfo(@PathVariable String chapterId){

        EduChapter byId = eduChapterService.getById(chapterId);

        return Result.ok().data("chapterInfo",byId);
    }

    /**
     * 添加章节
     * @param eduChapter
     * @return
     */
    @PostMapping("/addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter){

        boolean save = eduChapterService.save(eduChapter);

        return save ? Result.ok() : Result.error();
    }

    /**
     * 根据课程ID查询 章节 小节
     * @param courseId
     * @return
     */
    @GetMapping("/getChapterVideoList/{courseId}")
    public Result getChapterVideoList(@PathVariable String courseId){

        List<ChapterVo> list = eduChapterService.getChapterVideoList(courseId);

        return Result.ok().data("items",list);
    }

}

