package com.rh.service;

import com.rh.model.CourseTeacher;
import com.rh.model.CourseTeacherInfo;
import com.rh.utils.Result;

/**
 * @author RH
 * @data 2023/6/12
 * @apiNote
 */
public interface CourseTeacherService {
    Result findCourseTeacherInfo(CourseTeacherInfo courseTeacherInfo);

    Result addCourse(CourseTeacher courseTeacher);

    Result del(Integer ctid);
}
