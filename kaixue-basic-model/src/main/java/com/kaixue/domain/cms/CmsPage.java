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
@Document(collection = "cms_page")
//@ApiModelProperty是swagger的注解，表示这个字段意义
public class CmsPage
{
    //站点Id
    @ApiModelProperty("站点id")
    private String siteId;

    @Id
    @ApiModelProperty("页面id")
    //页面Id
    private String pageId;
    //页面名称
    @ApiModelProperty("页面名称")
    private String pageName;
    //页面别名
    @ApiModelProperty("页面别名")
    private String pageAliase;
    //访问地址
    @ApiModelProperty("访问地址")
    private String pageWebPath;
    //参数
    @ApiModelProperty("参数")
    private String pageParameter;
    //页面物理地址
    @ApiModelProperty("页面物理地址")
    private String pagePhysicalPath;
    //页面类型（静态/动态）
    @ApiModelProperty("页面类型")
    private String pageType;
    //页面模板
    @ApiModelProperty("页面模板")
    private String pageTemplate;
    //页面静态化内容
    @ApiModelProperty("页面静态化内容")
    private String pageHtml;
    //状态
    @ApiModelProperty("状态")
    private String pageStatus;
    //创建时间
    @ApiModelProperty("创建时间")
    private String pageCreateTime;
    //模板Id
    @ApiModelProperty("模板id")
    private String templateId;
    //参数列表
    @ApiModelProperty("参数列表")
    private List<CmsPageParam> pageParams;
    //模板文件Id
//    private String templateFileId;
    //静态文件Id
    @ApiModelProperty("静态文件id")
    private String htmlFileId;
    //数据url
    @ApiModelProperty("数据url")
    private String dataUrl;
}
