package com.rh.service;


import com.rh.model.Admin;
import com.rh.utils.Result;




public interface AdminService {
    Result login(Admin admin);

    Result findById(Integer id);

    Result edit(Admin admin);

}
