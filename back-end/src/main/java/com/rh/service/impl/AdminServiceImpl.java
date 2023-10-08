package com.rh.service.impl;

import com.rh.mapper.AdminMapper;
import com.rh.model.Admin;
import com.rh.service.AdminService;
import com.rh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author RH
 * @data 2023/6/3
 * @apiNote
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Result login(Admin admin) {
        Admin result = adminMapper.findById(admin.getAdminId());
        if (result == null) {
            return Result.error("用户不存在");
        }
        Admin admins = adminMapper.login(admin.getAdminId(), admin.getPassword());
        if (admins == null) {
            return Result.error("密码错误");
        }
        admins.setPassword("");
        return Result.ok(200, "登录成功", admins);
    }
    @Override
    public Result findById(Integer id) {
        Admin result = adminMapper.findById(id);
        if (result == null) {
            return Result.error("数据查询失败");
        }
        result.setPassword("");
        return Result.ok(200, "个人信息查询成功", result);
    }

    @Override
    public Result edit(Admin admin) {
        int i = adminMapper.edit(admin);
        if (i == 1) {
            return Result.ok(200, "更改成功", "");
        }
        return Result.error("系统异常，更改失败！");
    }
}
