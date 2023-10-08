package com.rh.service;


import com.rh.model.CourseTeacherInfo;
import com.rh.model.Score;
import com.rh.utils.Result;

/**
 * @author RH
 * @data 2023/6/12
 * @apiNote
 */
public interface ScoreService {
    Result findScore(Integer student_id);

    Result insertScore(Score score);

    Result delete(Integer sctid);

    Result studentScore(CourseTeacherInfo courseTeacherInfo);

    Result editScore(Score score);

    Result studentClass(Integer teacher_id);
}
