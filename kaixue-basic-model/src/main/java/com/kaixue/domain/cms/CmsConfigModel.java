package com.kaixue.domain.cms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

//以下两个注解用于自动生成get，set，equals等方法，属于lombok插件
@Data
@ToString
//@ApiModelProperty是swagger的注解，表示这个字段意义
public class CmsConfigModel
{
    //主键
    @ApiModelProperty("主键")
    private String key;
    //项目名称
    @ApiModelProperty("项目名称")
    private String name;
    //项目url
    @ApiModelProperty("项目url")
    private String url;
    //项目复杂值
    @ApiModelProperty("项目复杂值")
    private Map map;
    //项目简单值
    @ApiModelProperty("项目简单值")
    private String value;
}
