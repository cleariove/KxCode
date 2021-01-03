package com.kaixue.api.cms;

import com.kaixue.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

//api开头的注解均是swagger插件的注解，用于自动生成api文档
//swagger用法可以参照https://blog.csdn.net/wyb880501/article/details/79576784
@Api(value = "cms配置管理",tags = {"cms配置管理接口，提供数据模型接口，查询接口"})
public interface CmsConfigControllerApi
{
    @ApiOperation("根据id查询CMS配置信息")
    @ApiImplicitParam(name="id",value = "id",required = true,paramType = "path",dataType = "string")
    CmsConfig getModel(String id);
}
