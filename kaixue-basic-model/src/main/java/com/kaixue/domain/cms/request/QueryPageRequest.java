package com.kaixue.domain.cms.request;

import com.kaixue.model.request.RequestData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "cms页面查询类", description = "cms页面查询具体参数")
public class QueryPageRequest extends RequestData
{
    //站点id
    //@ApiModelProperty("")注解是swagger文档注解，用于自动生成的api文档中表明字段含义
    @ApiModelProperty("站点id")
    private String siteId;
    //页面id
    @ApiModelProperty("页面id")
    private String pageId;
    //页面名称
    @ApiModelProperty("页面名称")
    private String pageName;
    //页面别名
    @ApiModelProperty("页面别名")
    private String pageAliase;
    //模板id
    @ApiModelProperty("模板id")
    private String templateId;
    //页面类型
    @ApiModelProperty("页面类型")
    private String pageType;

}
