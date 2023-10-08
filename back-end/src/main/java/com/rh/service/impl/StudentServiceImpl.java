package com.rh.service.impl;

import com.rh.mapper.StudentMapper;
import com.rh.model.Student;
import com.rh.service.StudentService;
import com.rh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RH
 * @data 2023/6/3
 * @apiNote
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public Result login(Student student) {
        Student result = studentMapper.findById(student.getStudentId());
        if (result == null) {
            return Result.error("用户不存在");
        }
        Student students = studentMapper.login(student.getStudentId(), student.getPassword());
        if (students == null) {
            return Result.error("密码错误");
        }
        students.setPassword("");
        return Result.ok(200, "登录成功", students);
    }

    @Override
    public Result findById(Integer id) {
        Student result = studentMapper.findById(id);
        if (result == null) {
            return Result.error("数据查询失败");
        }
        result.setPassword("");
        return Result.ok(200, "个人信息查询成功", result);
    }

    @Override
    public Result edit(Student student) {
        int i = studentMapper.edit(student);
        if (i == 1) {
            return Result.ok(200, "更改成功", "");
        }
        return Result.error("系统异常，更改失败！");
    }

    @Override
    public Result add(Student student) {
        Student result = studentMapper.findById(student.getStudentId());
        if (result != null ) {
            return Result.error("该学号已注册");
        }
        int rows = studentMapper.add(student);
        if (rows == 1) {
            return Result.ok(200, "添加成功", "");
        }
        return Result.error("系统异常，添加失败！");
    }

    @Override
    public Result findStudent(Student student) {
        try {
            List<Student> list = studentMapper.findStudent(student.getStudentId(), student.getStudentName());
            if (list != null && list.size() != 0) {
                return Result.ok(200, "查询成功", list);
            }
            return Result.error("查询失败");
        } catch (Exception e) {
            return Result.error("查询失败");
        }
    }

    @Override
    public Result del(Integer studentId) {
        try {
            int rows = studentMapper.del(studentId);
            if (rows == 1) {
                return Result.ok(200, "删除成功", "");
            }
            return Result.error("删除失败！");
        } catch (Exception e) {
            return Result.error("删除失败！");
        }
    }

}
