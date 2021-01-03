package com.kaixue.api.cms;

import com.kaixue.domain.cms.CmsTemplate;
import com.kaixue.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//api开头的注解均是swagger插件的注解，用于自动生成api文档
//swagger用法可以参照https://blog.csdn.net/wyb880501/article/details/79576784
@Api(value = "cms模板接口",tags = {"提供页面模板的增删改查"})
public interface CmsTemplateControllerApi
{
    @ApiOperation(value = "查询全部页面模板")
    QueryResponseResult<CmsTemplate> findAll();
}
