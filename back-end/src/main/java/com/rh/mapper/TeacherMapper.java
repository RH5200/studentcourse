package com.rh.mapper;

import com.rh.model.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    List<Teacher> findAll();
    Teacher findById(Integer teacherId);

    Teacher login(Integer teacherId, String password);

    List<Teacher> findTeacher(@Param("teacher_id") Integer teacher_id,
                              @Param("teacher_name") String teacher_name);

    int edit(Teacher teacher);

    int add(Teacher teacher);

    int del(Integer teacherId);


}
