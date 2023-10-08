package com.rh.mapper;

import com.rh.model.Grade;

import java.util.List;

public interface GradeMapper {
    List<Grade> findGrade();
    int deleteByPrimaryKey(Integer gradeId);

    int insert(Grade record);

    int insertSelective(Grade record);

    Grade selectByPrimaryKey(Integer gradeId);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);
}
