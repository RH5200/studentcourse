package com.rh.service.impl;

import com.rh.mapper.GradeMapper;
import com.rh.model.Grade;
import com.rh.service.GradeService;
import com.rh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RH
 * @data 2023/7/1
 * @apiNote
 */
@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeMapper gradeMapper;
    @Override
    public Result findGrade() {
        try{
            List<Grade> grade = gradeMapper.findGrade();
            return Result.ok(200, "查询成功", grade);
        }catch (Exception e){
            return Result.error("查询失败");
        }
    }
}
