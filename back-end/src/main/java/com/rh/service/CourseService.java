package com.rh.service;

import com.rh.model.Course;
import com.rh.utils.Result;

/**
 * @author RH
 * @data 2023/6/13
 * @apiNote
 */
public interface CourseService {
    Result findCourse(Course course);

    Result findTeacherCourse(Integer teacher_Id);

    Result del(Integer courseId);

    Result edit(Course course);

    Result add(Course course);

}
