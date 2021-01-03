package com.kaixue.api.cms;

import com.kaixue.domain.cms.CmsPage;
import com.kaixue.domain.cms.request.QueryPageRequest;
import com.kaixue.domain.cms.response.CmsPageResponse;
import com.kaixue.model.response.QueryResponseResult;
import com.kaixue.model.response.ResponseResult;
import io.swagger.annotations.*;

//api开头的注解均是swagger插件的注解，用于自动生成api文档
//swagger用法可以参照https://blog.csdn.net/wyb880501/article/details/79576784
@Api(value = "cms页面管理接口",tags = {"提供页面的增删改查"})
public interface CmsPageControllerApi
{
    @ApiOperation(value = "分页列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required = true,
                    paramType = "path",dataType = "int"),
            @ApiImplicitParam(name="size",value = "页面大小",required = true,
                    paramType = "path",dataType = "int")
    })
    QueryResponseResult<CmsPage> findList(int page, int size, QueryPageRequest queryPageRequest);

    @ApiOperation(value = "新增页面")
    CmsPageResponse add(CmsPage cmsPage);

    @ApiOperation(value = "根据Id查找页面")
    CmsPageResponse findById(String id);

    @ApiOperation(value = "修改页面")
    @ApiImplicitParam(name = "id",value = "页面id",required = true,paramType = "path", dataType = "string")
    CmsPageResponse edit(String id, CmsPage cmsPage);

    @ApiOperation(value = "删除页面")
    @ApiImplicitParam(name = "id",value = "页面id",required = true,paramType = "path", dataType = "string")
    ResponseResult delete(String id);
}
