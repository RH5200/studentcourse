package com.rh.controller;

import com.rh.service.GradeService;
import com.rh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RH
 * @data 2023/7/1
 * @apiNote
 */
@RestController
@RequestMapping("grade")
public class GradeController {
    @Autowired
    GradeService gradeService;
    @PostMapping("/findGrade")
    Result findGrade(){
        try {
            return gradeService.findGrade();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"系统异常，查询失败",e.getMessage());
        }
    }
}
