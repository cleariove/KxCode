package com.kaixue.api.sys;

import com.kaixue.domain.system.response.SysDictionaryResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "数据字典接口",tags = "提供数据字典接口的管理、查询功能")
public interface SysDictionaryControllerApi
{
    @ApiOperation(value="数据字典查询接口")
    SysDictionaryResponse findByType(String type);
}
