package com.rh.service.impl;

import com.rh.mapper.CourseMapper;
import com.rh.model.Course;
import com.rh.model.CourseTeacherInfo;
import com.rh.service.CourseService;
import com.rh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RH
 * @data 2023/6/13
 * @apiNote
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public Result findCourse(Course course) {
        try {
            List<CourseTeacherInfo> courseInfo1 = courseMapper.findCourse(course.getCourseId(), course.getCourseName());
            if (courseInfo1 != null && courseInfo1.size() != 0) {
                return Result.ok(200, "查询成功", courseInfo1);
            }
            return Result.error("查询失败");
        } catch (Exception e) {
            return Result.error("查询失败");
        }
    }

    @Override
    public Result findTeacherCourse(Integer teacher_Id) {
        try {
            List<CourseTeacherInfo> teacherCourse = courseMapper.findTeacherCourse(teacher_Id);
            return Result.ok(200, "查询成功", teacherCourse);
        } catch (Exception e) {
            return Result.error("查询失败");
        }
    }

    @Override
    public Result del(Integer courseId) {
        try {
            int rows = courseMapper.del(courseId);
            if (rows == 1) {
                return Result.ok(200, "删除成功", "");
            }
            return Result.error("删除失败！");
        } catch (Exception e) {
            return Result.error("删除失败！");
        }
    }

    @Override
    public Result edit(Course course) {
        int i = courseMapper.edit(course);
        if (i == 1) {
            return Result.ok(200, "更改成功", "");
        }
        return Result.error("系统异常，更改失败！");
    }

    @Override
    public Result add(Course course) {
        try {
            courseMapper.checkAdd();
            int add = courseMapper.add(course);
            if(add == 1){
                return Result.ok("添加成功");
            }
            return  Result.error("添加失败");
        }catch (Exception e) {
            return Result.error("添加失败");
        }
    }


}
