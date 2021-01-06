package com.kaixue.manage_cms_client;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//因为先引入了mysql连接包但是没有配置，所以注入会报错，这个注解可以模拟一个连接对象
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@ComponentScan(basePackages = {"com.kaixue.model"})
@ComponentScan(basePackages = {"com.kaixue.domain.cms"})
@ComponentScan(basePackages = {"com.kaixue.manage_cms_client"})
public class ManageCmsClientApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ManageCmsClientApplication.class,args);
    }
}
