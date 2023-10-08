package com.rh.controller;

import com.rh.model.CourseTeacher;
import com.rh.model.CourseTeacherInfo;
import com.rh.service.CourseTeacherService;
import com.rh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/courseTeacher")
public class CourseTeacherController {
    @Autowired
    CourseTeacherService courseTeacherService;

    @PostMapping("/findInfo")
    public Result findInfo(@RequestBody CourseTeacherInfo courseTeacherInfo){
        try {
            return courseTeacherService.findCourseTeacherInfo(courseTeacherInfo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    @PostMapping("/addCourse")
    public Result addCourse(@RequestBody CourseTeacher courseTeacher){
        try {
            return courseTeacherService.addCourse(courseTeacher);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    @PostMapping("/del")
    public Result del(@RequestBody Integer ctid){
        try {
            return courseTeacherService.del(ctid);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }
}
