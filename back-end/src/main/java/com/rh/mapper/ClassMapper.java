package com.rh.mapper;

import com.rh.model.Class;

import java.util.List;

public interface ClassMapper {
    List<Class> findClass();

    int deleteByPrimaryKey(Integer classId);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Integer classId);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
}
