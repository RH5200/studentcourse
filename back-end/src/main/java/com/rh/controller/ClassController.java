package com.rh.controller;

import com.rh.service.ClassService;
import com.rh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RH
 * @data 2023/6/21
 * @apiNote
 */
@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    ClassService classService;
    @PostMapping("/findClass")
    public Result findClass(){
        try {
            return classService.findClass();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }
}
