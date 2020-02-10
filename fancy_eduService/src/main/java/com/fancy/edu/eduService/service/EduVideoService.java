package com.fancy.edu.eduService.service;

import com.fancy.edu.eduService.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Joah
 * @since 2020-02-09
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 根据课程ID删除小节
     * @param id
     * @return
     */
    boolean deleteVideoByCourseId(String id);

    /**
     * 删除小节
     * @param videoId
     * @return
     */
    boolean removeVideo(String videoId);
}
