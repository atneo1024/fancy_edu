package com.fancy.edu.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fancy.edu.eduService.entity.EduSubject;
import com.fancy.edu.eduService.entity.vo.SubjectNestedVo;
import com.fancy.edu.eduService.entity.vo.SubjectVo;
import com.fancy.edu.eduService.handler.ServiceException;
import com.fancy.edu.eduService.mapper.EduSubjectMapper;
import com.fancy.edu.eduService.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Joah
 * @since 2020-02-01
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    /**
     * 读取Excel的内容
     * @param file
     * @return
     */
    @Override
    @Transactional
    public List<String> importExcelSubject(MultipartFile file) {
        // 存储错误信息
        List<String> mesg = new ArrayList<>();

        try {
            // 1、获取文件输入流
            InputStream inputStream = file.getInputStream();

            // 2、创建workbook
            Workbook workbook = new XSSFWorkbook(inputStream);

            // 3、根据 workbook获取sheet
            Sheet sheet = workbook.getSheetAt(0);

            // 4、sheet获取row
            // 循环遍历获取行，从第二行开始取数据
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++){
                Row row = sheet.getRow(i);
                if (row == null){
                    String str = "第" + i + "行,第" + 0 + "列数据为空";
                    mesg.add(str);
                    continue;
                }
                // 5、row获取cell, 第一列
                Cell cellOne = row.getCell(0);
                // 判断列是否为空
                if (cellOne == null){
                    String str = "第" + i + "行,第" + 0 + "列数据为空";
                    mesg.add(str);
                    continue;
                }
                // 一级分类的值
                String cellOneValue = cellOne.getStringCellValue();
                // 添加一级分类之前，判断要添加的名称，是否在库中存在
                EduSubject existOneSubject = this.existOneSubject(cellOneValue);
                // 存贮一级分类的 id
                String parentId;
                if (ObjectUtils.isEmpty(existOneSubject)){
                    EduSubject eduSubject = new EduSubject();
                    eduSubject.setTitle(cellOneValue);
                    eduSubject.setParentId("0");
                    eduSubject.setSort(0);
                    baseMapper.insert(eduSubject);
                    // 新添加的一级 id 获取到
                    parentId = eduSubject.getId();
                }else {
                    parentId = existOneSubject.getId();
                }

                // 5.1、获取第二列
                Cell cellTow = row.getCell(1);
                // 判断列是否为空
                if (cellTow == null){
                    String str = "第" + i + "行,第" + 1 + "列数据为空";
                    mesg.add(str);
                    continue;
                }
                // 二级分类
                // 6、cell获取 cellValue
                String cellTowValue = cellTow.getStringCellValue();
                // 添加二级分类，如果存在，不添加  如果不存在 添加
                EduSubject twoSubject = this.existTwoSubject(cellTowValue, parentId);
                if (ObjectUtils.isEmpty(twoSubject)){
                    EduSubject eduTwoSubject = new EduSubject();
                    eduTwoSubject.setTitle(cellTowValue);
                    eduTwoSubject.setParentId(parentId);
                    eduTwoSubject.setSort(0);
                    // 7、把获取打的值添加到数据库
                    baseMapper.insert(eduTwoSubject);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException(20001,"导入异常");
        }

        return mesg;
    }

    /**
     * 获取一级分类列表
     * @return
     */
    @Override
    public List<SubjectNestedVo> nestedList() {

        //最终要的到的数据列表
        ArrayList<SubjectNestedVo> subjectNestedVoArrayList = new ArrayList<>();

        //获取一级分类数据记录
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> subjects = baseMapper.selectList(queryWrapper);

        //获取二级分类数据记录
        //TODO

        //填充一级分类vo数据
        int count = subjects.size();
        for (int i = 0; i < count; i++) {
            EduSubject subject = subjects.get(i);

            //创建一级类别vo对象
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(subject, subjectNestedVo);
            subjectNestedVoArrayList.add(subjectNestedVo);

            //填充二级分类vo数据
            QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.ne("parent_id", 0);
            queryWrapper2.orderByAsc("sort", "id");
            List<EduSubject> subSubjects = baseMapper.selectList(queryWrapper2);

            ArrayList<SubjectVo> subjectVoArrayList = new ArrayList<>();
            int count2 = subSubjects.size();
            for (int j = 0; j < count2; j++) {

                EduSubject subSubject = subSubjects.get(j);
                if(subject.getId().equals(subSubject.getParentId())){
                    //创建二级类别vo对象
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(subSubject, subjectVo);
                    subjectVoArrayList.add(subjectVo);
                }
            }
            subjectNestedVo.setChildren(subjectVoArrayList);
        }

        return subjectNestedVoArrayList;
    }

    /**
     * 删除一级分类
     * @param id
     * @return
     */
    @Override
    public boolean deleteSubjectById(String id) {

        // 判断一级分类下是否有二级分类
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        Integer integer = baseMapper.selectCount(queryWrapper);

        return integer > 0 ? false : baseMapper.deleteById(id) > 0;
    }

    /**
     * 添加一级分类
     * @param eduSubject
     * @return
     */
    @Override
    public boolean saveOneLevel(EduSubject eduSubject) {

        // 判断一级分类是否存在
        EduSubject existOneSubject = this.existOneSubject(eduSubject.getTitle());

        if (ObjectUtils.isEmpty(existOneSubject)){
            // 添加
            eduSubject.setParentId("0");
            int result = baseMapper.insert(eduSubject);

            return result > 0;
        }

        return false;
    }

    /**
     * 添加二级分类
     * @param eduSubject
     * @return
     */
    @Override
    public boolean saveTwoLevel(EduSubject eduSubject) {

        // 判断二级分类是否存在
        EduSubject existTwoSubject = this.existTwoSubject(eduSubject.getTitle(), eduSubject.getParentId());

        if (ObjectUtils.isEmpty(existTwoSubject)){
            // 添加
            int result = baseMapper.insert(eduSubject);

            return result > 0;
        }


        return false;
    }

    /**
     * 判断二级分类是否存在
     * @param name
     * @param parentId
     * @return
     */
    private EduSubject existTwoSubject(String name, String parentId){

        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",parentId);

        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 判断数据库表中是否存在一级分类
     * @param name
     * @return
     */
    private EduSubject existOneSubject(String name){

        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id","0");

        return baseMapper.selectOne(queryWrapper);
    }
}
