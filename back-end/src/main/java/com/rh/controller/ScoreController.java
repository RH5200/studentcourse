package com.rh.controller;

import com.rh.model.CourseTeacherInfo;
import com.rh.model.Score;
import com.rh.service.ScoreService;
import com.rh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    ScoreService scoreService;

    @PostMapping("/findScore")
    public Result findScore(@RequestBody Integer student_id){
        try {
            return scoreService.findScore(student_id);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    @PostMapping("/insertScore")
    public Result insertScore(@RequestBody Score score){
        try{
            return scoreService.insertScore(score);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    @PostMapping("/del")
    public Result del(@RequestBody Integer scid){
        try{
            return scoreService.delete(scid);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    @PostMapping("/studentScore")
    public Result studentScore(@RequestBody CourseTeacherInfo courseTeacherInfo){
        try{
            return scoreService.studentScore(courseTeacherInfo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    @PostMapping("/editScore")
    public Result editScore(@RequestBody Score score){
        try{
            return scoreService.editScore(score);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    @PostMapping("/studentClass")
    public Result studentClass(@RequestBody Integer teacher_id){
        try{
            return scoreService.studentClass(teacher_id);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }
}
