package com.rh.mapper;

import com.rh.model.CourseTeacherInfo;
import com.rh.model.CourseTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseTeacherMapper {
    int del(Integer ctid);

    int addCourse(CourseTeacher record);

    List<CourseTeacher> addCheck(@Param("course_id") Integer course_id,
                                 @Param("teacher_id") Integer teacher_id);


    CourseTeacher selectByPrimaryKey(Integer ctid);

    List<CourseTeacher> findAll();

    int updateByPrimaryKeySelective(CourseTeacher record);

    int updateByPrimaryKey(CourseTeacher record);


    List<CourseTeacher> findCourseTeacher(@Param("course_id") Integer course_id,
                                   @Param("course_name") String course_name,
                                   @Param("teacher_id") Integer teacher_id,
                                   @Param("course_credit") Integer course_credit,
                                   @Param("teacher_name") String teacher_name);

    List<CourseTeacherInfo> findCourseTeacherInfo(@Param("course_name") String course_name,
                                                  @Param("teacher_id") Integer teacher_id,
                                                  @Param("teacher_name") String teacher_name);

}
