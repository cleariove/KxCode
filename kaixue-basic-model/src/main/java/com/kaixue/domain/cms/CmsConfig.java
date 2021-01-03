package com.kaixue.domain.cms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//以下两个注解用于自动生成get，set，equals等方法，属于lombok插件
@Data
@ToString
//这个注解表明了该类在MongoDB中对应的collection名称
@Document(collection = "cms_config")
//@ApiModelProperty是swagger的注解，表示这个字段意义
public class CmsConfig
{
    @Id
    //主键
    @ApiModelProperty("主键")
    private String id;
    //数据模型名称
    @ApiModelProperty("数据模型名称")
    private String name;
    //数据模型项目
    @ApiModelProperty("数据模型项目")
    private List<CmsConfigModel> model;
}
