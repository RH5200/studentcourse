package com.rh.service.impl;

import com.rh.mapper.TeacherMapper;
import com.rh.model.Teacher;
import com.rh.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public Result findAll() {
        List<Teacher> all = teacherMapper.findAll();
        if(all!=null && all.size()!=0) {
            return Result.ok(200, "查询成功", all);
        }
        return Result.error("查询失败");
    }

    @Override
    public Result login(Teacher teacher) {
        Teacher result = teacherMapper.findById(teacher.getTeacherId());
        if (result == null) {
            return Result.error("用户不存在");
        }
        Teacher teachers = teacherMapper.login(teacher.getTeacherId(), teacher.getPassword());
        if (teachers == null) {
            return Result.error("密码错误");
        }
        teachers.setPassword("");
        return Result.ok(200, "登录成功", teachers);
    }

    @Override
    public Result findById(Integer id) {
        Teacher result = teacherMapper.findById(id);
        if (result == null) {
            return Result.error("数据查询失败");
        }
        result.setPassword("");
        return Result.ok(200, "个人信息查询成功", result);
    }

    @Override
    public Result edit(Teacher teacher) {
        int i = teacherMapper.edit(teacher);
        if (i == 1) {
            return Result.ok(200, "更改成功", "");
        }
        return Result.error("系统异常，更改失败！");
    }

    @Override
    public Result add(Teacher teacher) {
        Teacher result = teacherMapper.findById(teacher.getTeacherId());
        if (result != null) {
            return Result.error("该职工号已注册");
        }
        int rows = teacherMapper.add(teacher);
        if (rows == 1) {
            return Result.ok(200, "添加成功", "");
        }
        return Result.error("系统异常，添加失败！");
    }

    @Override
    public Result findTeacher(Teacher teacher) {
        try {
            List<Teacher> list = teacherMapper.findTeacher(teacher.getTeacherId(), teacher.getTeacherName());
            if (list != null && list.size() != 0) {
                return Result.ok(200, "查询成功", list);
            }
            return Result.error("查询失败");
        } catch (Exception e) {
            return Result.error("查询失败");
        }
    }

    @Override
    public Result del(Integer teacherId) {
        try {
            int rows = teacherMapper.del(teacherId);
            if (rows == 1) {
                return Result.ok(200, "删除成功", "");
            }
            return Result.error("删除失败！");
        } catch (Exception e) {
            return Result.error("删除失败！");
        }
    }

}

