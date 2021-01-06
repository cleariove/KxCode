package com.kaixue.domain.cms;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

//以下两个注解用于自动生成get，set，equals等方法，属于lombok插件
@Data
@ToString
//这个注解表明了该类在MongoDB中对应的collection名称
@Document(collection = "cms_site")
//@ApiModelProperty是swagger的注解，表示这个字段意义
public class CmsSite
{
    @Id
    private String siteId;
    //站点名称
    @ApiModelProperty("站点名称")
    private String siteName;
    //站点url域
    @ApiModelProperty("站点url域")
    private String siteDomain;
    //站点端口,int类型值在mongodb中不能为空或者非数字串
    @ApiModelProperty("站点端口")
    private int sitePort;
    //站点web路径
    @ApiModelProperty("站点web路径")
    private String siteWebPath;
    //创建时间
    @ApiModelProperty("创建时间")
    private Date siteCreateDate;
    //站点物理路径
    @ApiModelProperty("站点物理路径")
    private String sitePhysicalPath;
}
