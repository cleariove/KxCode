package com.kaixue.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//这个类用于配置swagger
@Configuration
@EnableSwagger2
public class Swagger2Config
{
    @Bean
    public Docket createRestApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //选择哪些接口暴露给swagger
                .select()
                //扫描的路径包,设置basePackage会将包下的所有被@Api标记类的所有方法作为api
                .apis(RequestHandlerSelectors.basePackage("com.kaixue"))
                //处理指定的路径，这个代表处理所有路径
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @return 文档基本信息
     */
    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder().
                title("恺学").//设置文档标题（API名称）
                description("恺学api文档").//文档副标题
                version("1.0").
                contact(new Contact("刘鑫恺","https://baidu.com","765357429@qq.com")).
                build();
    }

}
