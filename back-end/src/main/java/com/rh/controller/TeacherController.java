package com.rh.controller;

import com.rh.model.Teacher;
import com.rh.service.TeacherService;
import com.rh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @PostMapping("/findAll")
    public Result findAll() {
        try {
            return teacherService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(500, "发生了异常", e.getMessage());
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody Teacher teacher) {
        try {
            return teacherService.login(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(500, "发生了异常", e.getMessage());
        }
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody Teacher teacher) {
        try {
            return teacherService.edit(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(500, "发生了异常", e.getMessage());
        }
    }

    @PostMapping("/findById")
    public Result findById(@RequestBody Integer id) {
        try {
            return teacherService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(500, "发生了异常", e.getMessage());
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody Teacher teacher) {
        try {
            return teacherService.add(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(500, "发生了异常", e.getMessage());
        }
    }

    @PostMapping("/findTeacher")
    public Result findTeacher(@RequestBody Teacher teacher) {
        try {
            return teacherService.findTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(500, "发生了异常", e.getMessage());
        }
    }

    @PostMapping("/del")
    public Result del(@RequestBody Integer teacherId) {
        try {
            return teacherService.del(teacherId);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(500, "发生了异常", e.getMessage());
        }
    }
}
