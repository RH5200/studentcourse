package com.rh.mapper;

import com.rh.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    Student findById(Integer sid);

    Student login(Integer studentId, String password);

    List<Student> findStudent(@Param("student_id") Integer student_id,
                              @Param("student_name") String student_name);

    int edit(Student student);

    int del(Integer studentId);

    int insert(Student student);

    int add(Student record);


    int updateByPrimaryKey(Student record);
}
