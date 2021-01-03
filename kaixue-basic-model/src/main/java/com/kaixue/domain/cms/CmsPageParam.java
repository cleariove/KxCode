package com.kaixue.domain.cms;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

//以下两个注解用于自动生成get，set，equals等方法，属于lombok插件
@Data
@ToString
//@ApiModelProperty是swagger的注解，表示这个字段意义
public class CmsPageParam
{
    //参数名称
    @ApiModelProperty("参数名称")
    private String pageParamName;
    //参数值
    @ApiModelProperty("参数值")
    private String pageParamValue;
}
