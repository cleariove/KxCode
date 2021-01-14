package com.kaixue.manage_course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.kaixue.domain.course"})//扫描model项目下所有实体类
@ComponentScan(basePackages = {"com.kaixue.api"})//扫描api项目下所有包
@ComponentScan(basePackages = {"com.kaixue.manage_course"})//扫描本项目
@ComponentScan(basePackages = {"com.kaixue.model"})//扫描common项目所有包
public class ManageCourseApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ManageCourseApplication.class,args);
    }
}
