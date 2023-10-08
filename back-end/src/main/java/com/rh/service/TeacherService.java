package com.rh.service;

import com.rh.model.Teacher;
import com.rh.utils.Result;




public interface TeacherService {
    Result findAll();
    Result login(Teacher teacher);

    Result findById(Integer id);

    Result edit(Teacher teacher);

    Result add(Teacher teacher);

    Result findTeacher(Teacher teacher);

    Result del(Integer teacherId);


}
