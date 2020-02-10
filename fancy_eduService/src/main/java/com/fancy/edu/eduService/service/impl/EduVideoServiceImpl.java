package com.fancy.edu.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fancy.edu.eduService.entity.EduVideo;
import com.fancy.edu.eduService.mapper.EduVideoMapper;
import com.fancy.edu.eduService.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Joah
 * @since 2020-02-09
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    /**
     * 根据课程ID删除小节
     * @param id
     * @return
     */
    @Override
    public boolean deleteVideoByCourseId(String id) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        int delete = baseMapper.delete(wrapper);
        return delete > 0;
    }

    /**
     * 根据小节ID删除
     * @param videoId
     * @return
     */
    @Override
    public boolean removeVideo(String videoId) {
        // TODO 删除小节的时候，还需要删除阿里云视频，预留

        int result = baseMapper.deleteById(videoId);


        return result > 0;
    }
}
