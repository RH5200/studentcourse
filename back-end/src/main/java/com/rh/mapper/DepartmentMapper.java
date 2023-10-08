package com.rh.mapper;

import com.rh.model.Department;

import java.util.List;

public interface DepartmentMapper {
    List<Department> findDepartment();
    int deleteByPrimaryKey(Integer departmentId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer departmentId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}
