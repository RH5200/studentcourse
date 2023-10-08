package com.rh.service.impl;

import com.rh.mapper.ScoreMapper;
import com.rh.model.CourseTeacherInfo;
import com.rh.model.Score;
import com.rh.service.ScoreService;
import com.rh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RH
 * @data 2023/6/12
 * @apiNote
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public Result findScore(Integer student_id) {
        try {
            List<CourseTeacherInfo> courseTeacherInfos = scoreMapper.findScore(student_id);
            if(courseTeacherInfos!=null && courseTeacherInfos.size()!=0){
                return Result.ok(200,"查询成功",courseTeacherInfos);
            }
            return Result.error("查询失败,数据库无该用户信息");
        }catch (Exception e) {
            return Result.error("查询失败");
        }
    }

    @Override
    public Result insertScore(Score score) {
        try{
            List<Score> one = scoreMapper.findOne(score);
            if(one != null && one.size()!=0){
                return Result.error("已有该课程");
            }
            int rows = scoreMapper.insertScore(score);
            if(rows!=0){
                return Result.ok(200,"添加成功","");
            }
            return Result.error("添加失败");
        }catch (Exception e){
            return Result.error("添加失败");
        }
    }

    @Override
    public Result delete(Integer sctid) {
        try{
            int rows = scoreMapper.deleteByPrimaryKey(sctid);
            if(rows!=0){
                return Result.ok(200,"删除成功","");
            }
            return Result.error("删除失败");
        }catch (Exception e){
            return Result.error("删除失败");
        }
    }

    @Override
    public Result studentScore(CourseTeacherInfo courseTeacherInfo) {
        try{
            List<CourseTeacherInfo> infos = scoreMapper.studentScore(courseTeacherInfo.getTeacherId(),courseTeacherInfo.getClassId(),courseTeacherInfo.getStudentId(),courseTeacherInfo.getStudentName());
            return Result.ok(200,"查询成功",infos);
        }catch (Exception e){
            return Result.error("查询错误");
        }
    }

    @Override
    public Result editScore(Score score) {
        try{
            int rows = scoreMapper.editScore(score.getSctid(),score.getScore());
            if(rows!=0){
                return Result.ok(200,"修改成功","");
            }
            return Result.error("修改出错");
        }catch (Exception e){
            return Result.error("修改失败");
        }
    }

    @Override
    public Result studentClass(Integer teacher_id) {
        try{
            List<CourseTeacherInfo> infos = scoreMapper.studentClass(teacher_id);
            if(infos!=null && infos.size()!=0){
                return Result.ok(200,"查询成功",infos);
            }
            return Result.error("查询失败");
        }catch (Exception e){
            return Result.error("查询失败");
        }
    }
}
