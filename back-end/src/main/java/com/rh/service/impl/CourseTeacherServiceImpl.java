package com.rh.service.impl;

import com.rh.mapper.CourseTeacherMapper;
import com.rh.model.CourseTeacher;
import com.rh.model.CourseTeacherInfo;
import com.rh.service.CourseTeacherService;
import com.rh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RH
 * @data 2023/6/12
 * @apiNote
 */
@Service
public class CourseTeacherServiceImpl implements CourseTeacherService {

    @Autowired
    CourseTeacherMapper courseTeacherMapper;
    @Override
    public Result findCourseTeacherInfo(CourseTeacherInfo courseTeacherInfo) {
        try{
            List<CourseTeacherInfo> infos = courseTeacherMapper.findCourseTeacherInfo(courseTeacherInfo.getCourseName(),courseTeacherInfo.getTeacherId(),courseTeacherInfo.getTeacherName());
            if(infos!=null&&infos.size()!=0){
                return  Result.ok(200,"查询成功",infos);
            }
            return Result.error("查询失败,数据库无该用户信息");
        }catch (Exception e){
            return Result.error("查询失败!");
        }
    }

    @Override
    public Result addCourse(CourseTeacher courseTeacher) {
        List<CourseTeacher> list = courseTeacherMapper.addCheck(courseTeacher.getCourseId(), courseTeacher.getTeacherId());
        if(list!=null&&list.size()!=0){
            return Result.error("该教师已开设该课程");
        }
        try{
            int rows = courseTeacherMapper.addCourse(courseTeacher);
            if(rows!=0){
                return Result.ok(200,"添加成功","");
            }
            return Result.error("添加失败");
        }catch (Exception e){
            return Result.error("添加失败");
        }
    }

    @Override
    public Result del(Integer ctid) {
        try{
            int rows = courseTeacherMapper.del(ctid);
            if(rows!=0){
                return Result.ok(200,"删除成功","");
            }
            return Result.error("删除失败");
        }catch (Exception e){
            return Result.error("删除失败");
        }
    }
}
