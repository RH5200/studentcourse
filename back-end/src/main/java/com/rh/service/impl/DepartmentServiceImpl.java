package com.rh.service.impl;

import com.rh.mapper.DepartmentMapper;
import com.rh.model.Department;
import com.rh.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    @Override
    public Result findDepartment() {
        try {
            List<Department> department = departmentMapper.findDepartment();
            return Result.ok(200,"查询成功",department);
        }catch (Exception e) {
            return Result.error("查询失败");
        }
    }
}
