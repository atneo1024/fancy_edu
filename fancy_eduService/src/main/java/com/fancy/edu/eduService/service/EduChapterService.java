package com.fancy.edu.eduService.service;

import com.fancy.edu.eduService.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fancy.edu.eduService.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Joah
 * @since 2020-02-09
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 根据课程ID删除章节信息
     * @param id
     * @return
     */
    boolean deleteChapterByCourseId(String id);

    /**
     * 根据课程ID获取章节、小节信息
     * @param courseId
     * @return
     */
    List<ChapterVo> getChapterVideoList(String courseId);

    /**
     * 根据章节ID删除章节信息
     * @param chapterId
     * @return
     */
    boolean removeByChapterId(String chapterId);
}
