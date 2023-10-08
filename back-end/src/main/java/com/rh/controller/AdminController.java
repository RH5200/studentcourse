package com.rh.controller;

import com.rh.model.Admin;
import com.rh.service.AdminService;
import com.rh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        try {
            return adminService.login(admin);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }
    @PostMapping("/edit")
    public Result edit(@RequestBody Admin admin) {
        try {
            return adminService.edit(admin);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(500, "发生了异常", e.getMessage());
        }
    }

    @PostMapping("/findById")
    public Result findById(@RequestBody Integer id){
        try {
            return adminService.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return  Result.error(500,"发生了异常",e.getMessage());
        }
    }
}
