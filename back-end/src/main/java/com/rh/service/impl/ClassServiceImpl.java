package com.rh.service.impl;

import com.rh.mapper.ClassMapper;
import com.rh.model.Class;
import com.rh.service.ClassService;
import com.rh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RH
 * @data 2023/6/21
 * @apiNote
 */
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    ClassMapper classMapper;

    @Override
    public Result findClass() {
        try{
            List<Class> classes = classMapper.findClass();
            if (classes != null && classes.size() != 0) {
                return Result.ok(200, "查询成功", classes);
            }
            return Result.error("查询失败");
        }catch (Exception e){
            return Result.error("查询失败");
        }


    }
}
