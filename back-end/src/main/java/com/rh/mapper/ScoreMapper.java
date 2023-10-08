package com.rh.mapper;

import com.rh.model.CourseTeacherInfo;
import com.rh.model.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreMapper {

    List<CourseTeacherInfo> findScore(@Param("student_id") Integer student_id);

    List<CourseTeacherInfo> studentScore(@Param("teacher_id") Integer teacher_id,
                                         @Param("class_id") Integer class_id,
                                         @Param("student_id") Integer student_id,
                                         @Param("student_name") String student_name);
    List<CourseTeacherInfo> studentClass(@Param("teacher_id") Integer teacher_id);

    int deleteByPrimaryKey(Integer sctid);

    int insert(Score record);

    int insertScore(Score record);

    List<Score> findOne(Score record);

    Score selectByPrimaryKey(Integer sctid);

    int editScore(@Param("sctid") Integer sctid,
                  @Param("score") Float score);


    int updateByPrimaryKey(Score record);
}
