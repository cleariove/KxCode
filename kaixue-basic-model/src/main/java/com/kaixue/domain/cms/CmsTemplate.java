package com.kaixue.domain.cms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//以下两个注解用于自动生成get，set，equals等方法，属于lombok插件
@Data
@ToString
//这个注解表明了该类在MongoDB中对应的collection名称
@Document(collection = "cms_template")
//@ApiModelProperty是swagger的注解，表示这个字段意义
public class CmsTemplate
{
   //模版ID
   @Id
   @ApiModelProperty("模版ID")
   private String templateId;
   //站点ID
   @ApiModelProperty("站点ID")
   private String siteId;
   //模版名称
   @ApiModelProperty("模版名称")
   private String templateName;
   //模版参数
   @ApiModelProperty("模版参数")
   private String templateParameter;
   //模版文件Id
   @ApiModelProperty("模版文件Id")
   private String templateFileId;

}
