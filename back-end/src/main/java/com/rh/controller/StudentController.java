package com.rh.controller;

import com.rh.model.Student;
import com.rh.service.StudentService;
import com.rh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/login")
    public Result login(@RequestBody Student student){
        try {
            return studentService.login(student);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody Student student){
        try {
            return studentService.edit(student);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    @PostMapping("/findById")
    public Result findById(@RequestBody Integer id){
        try {
            return studentService.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return  Result.error(500,"发生了异常",e.getMessage());
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody Student student){
        try {
            return studentService.add(student);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    @PostMapping("/findStudent")
    public Result findStudent(@RequestBody Student student){
        try {
            return studentService.findStudent(student);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    @PostMapping("/del")
    public Result del(@RequestBody Integer studentId){
        try {
            return studentService.del(studentId);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }
}
