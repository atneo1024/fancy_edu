package com.fancy.edu.eduService.service;

import com.fancy.edu.commonUtil.result.Result;
import com.fancy.edu.eduService.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fancy.edu.eduService.entity.vo.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Joah
 * @since 2020-02-01
 */
public interface EduSubjectService extends IService<EduSubject> {

    List<String> importExcelSubject(MultipartFile file);

    List<SubjectNestedVo> nestedList();

    boolean deleteSubjectById(String id);
}
