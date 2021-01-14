package com.kaixue.manage_cms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

//因为先引入了mysql连接包但是没有配置，所以注入会报错，这个注解可以模拟一个连接对象
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
//@EntityScan作用 https://blog.csdn.net/andy_zhang2007/article/details/84099595#EntityScan_20
@EntityScan("com.kaixue.basic.domain.cms")//扫描实体类
//@ComponentScan扫描的bean需要带有注解
@ComponentScan(basePackages = {"com.kaixue.api"})//扫描api项目下所有包
@ComponentScan(basePackages = {"com.kaixue.manage_cms"})//扫描本项目下所有包
@ComponentScan(basePackages = {"com.kaixue.model"})//扫描common项目所有包
public class ManageCmsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ManageCmsApplication.class,args);
    }

    @Bean
    public RestTemplate restTemplate()
    {
        //默认使用JDK 自带的HttpURLConnection作为底层实现
        //这里指定使用的底层http库
        //参见https://www.cnblogs.com/zimug/archive/2020/08/03/13424276.html
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
