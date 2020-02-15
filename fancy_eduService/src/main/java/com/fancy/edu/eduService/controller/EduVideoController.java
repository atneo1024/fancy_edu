package com.fancy.edu.eduService.controller;


import com.fancy.edu.commonUtil.result.Result;
import com.fancy.edu.eduService.entity.EduVideo;
import com.fancy.edu.eduService.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Joah
 * @since 2020-02-09
 */
@CrossOrigin
@RestController
@RequestMapping("/eduService/edu-video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    /**
     * 根据小节ID删除小节
     * @param videoId
     * @return
     */
    @DeleteMapping("{videoId}")
    public Result deleteById(@PathVariable String videoId){

        boolean flag = eduVideoService.removeVideo(videoId);

        return flag ? Result.ok() : Result.error();
    }

    /**
     * 修改小节
     * @param eduVideo
     * @return
     */
    @PostMapping("/updateVideo")
    public Result updateVideo(@RequestBody EduVideo eduVideo){

        boolean result = eduVideoService.updateById(eduVideo);

        return result ? Result.ok() : Result.error();
    }

    /**
     * 根据ID查询小节
     * @param videoId
     * @return
     */
    @GetMapping("/getVideoInfo/{videoId}")
    public Result getVideoInfo(@PathVariable String videoId){

        EduVideo byId = eduVideoService.getById(videoId);

        return Result.ok().data("videoService", byId);
    }

    /**
     * 添加小节
     * @param eduVideo
     * @return
     */
    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo){

        boolean save = eduVideoService.save(eduVideo);

        return save ? Result.ok() : Result.error();
    }
}

