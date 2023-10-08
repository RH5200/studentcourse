package com.rh.service;

import com.rh.model.Student;
import com.rh.utils.Result;



public interface StudentService {
    Result login(Student student);

    Result edit(Student student);

    Result findById(Integer id);

    Result add(Student student);

    Result findStudent(Student student);

    Result del(Integer studentId);


}
