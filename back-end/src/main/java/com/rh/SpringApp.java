package com.rh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author RH
 * @data 2023/4/17
 * @apiNote
 */
@SpringBootApplication
@MapperScan("com.rh.mapper")
public class SpringApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class,args);
    }
}
