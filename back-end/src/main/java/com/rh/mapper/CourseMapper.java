package com.rh.mapper;

import com.rh.model.Course;
import com.rh.model.CourseTeacherInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    List<CourseTeacherInfo> findCourse(@Param("course_id") Integer course_id,
                                       @Param("course_name") String course_name);

    List<CourseTeacherInfo> findTeacherCourse(@Param("teacher_id") Integer teacher_id);
    int checkAdd();


    int del(Integer courseId);

    int add(Course record);

    int insert(Course record);

    Course selectByPrimaryKey(Integer courseId);

    int edit(Course record);

    int updateByPrimaryKey(Course record);
}
