package com.rh.controller;

import com.rh.model.Course;
import com.rh.service.CourseService;
import com.rh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("/findCourse")
    public Result findCourse(@RequestBody Course course){
        try {
            return courseService.findCourse(course);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"系统异常",e.getMessage());
        }
    }
    @PostMapping("/findTeacherCourse")
    public Result findTeacherCourse(@RequestBody Integer teacher_id){
        try {
            return courseService.findTeacherCourse(teacher_id);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"系统异常",e.getMessage());
        }
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody Course course){
        try {
            return courseService.edit(course);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    @PostMapping("/del")
    public Result del(@RequestBody Integer courseId){
        try {
            return courseService.del(courseId);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody Course course){
        try {
            return courseService.add(course);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }


}
