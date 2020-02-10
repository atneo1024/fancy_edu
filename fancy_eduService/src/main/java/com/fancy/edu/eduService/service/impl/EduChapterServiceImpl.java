package com.fancy.edu.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fancy.edu.eduService.entity.EduChapter;
import com.fancy.edu.eduService.entity.EduVideo;
import com.fancy.edu.eduService.entity.vo.ChapterVo;
import com.fancy.edu.eduService.entity.vo.VideoVo;
import com.fancy.edu.eduService.handler.ServiceException;
import com.fancy.edu.eduService.mapper.EduChapterMapper;
import com.fancy.edu.eduService.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fancy.edu.eduService.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.sql.rowset.serial.SerialException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Joah
 * @since 2020-02-09
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    /**
     * 小节
     */
    @Autowired
    private EduVideoService eduVideoService;

    /**
     * 根据课程ID删除章节信息
     * @param id
     * @return
     */
    @Override
    public boolean deleteChapterByCourseId(String id) {

        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);

        int delete = baseMapper.delete(wrapper);

        return delete > 0;
    }

    /**
     * 根据课程ID获取章节、小节信息
     * @param courseId
     * @return
     */
    @Override
    public List<ChapterVo> getChapterVideoList(String courseId) {

        // 最终返回的数据
        List<ChapterVo> chapterVos = new ArrayList<>();

        // 1、根据课程ID查询 章节信息
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);

        // 2、根据课程ID查询 小节信息
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoService.list(queryWrapper);

        // 3、遍历 章节 赋值到vo中
        if (!CollectionUtils.isEmpty(eduChapters)){
            eduChapters.forEach(chapter ->{
                ChapterVo chapterVo = new ChapterVo();
                List<VideoVo> children = new ArrayList<>();
                BeanUtils.copyProperties(chapter,chapterVo);
                // 4、添加小节
                if ( !CollectionUtils.isEmpty(eduVideos)){
                    eduVideos.forEach(video -> {
                        if (chapter.getId().equals(video.getChapterId())){
                            VideoVo videoVo = new VideoVo();
                            BeanUtils.copyProperties(video,videoVo);
                            children.add(videoVo);
                        }
                    });
                }
                chapterVo.setChildren(children);
                // vo 放入最终返回的list中
                chapterVos.add(chapterVo);
            });
        }

        return chapterVos;
    }

    /**
     * 根据章节ID删除章节信息
     * @param chapterId
     * @return
     */
    @Override
    public boolean removeByChapterId(String chapterId) {

        // 1、如果章节有小节 则 不删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        List<EduVideo> list = eduVideoService.list(wrapper);
        if (!CollectionUtils.isEmpty(list)){
            throw new ServiceException(20001,"该章节中存在小节，不允许删除");
        }
        // 2、如果没有小节 进行删除
        int i = baseMapper.deleteById(chapterId);

        return i > 0;
    }
}
