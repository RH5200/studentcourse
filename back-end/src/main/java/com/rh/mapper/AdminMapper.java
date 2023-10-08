package com.rh.mapper;

import com.rh.model.Admin;

import java.util.List;

public interface AdminMapper {
    Admin findById(Integer aid);

    Admin login(Integer adminId, String password);

    int edit(Admin admin);

    List<Admin> list();


    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    int updateByPrimaryKey(Admin record);
}
