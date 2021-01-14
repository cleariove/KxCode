package com.kaixue.api.course;

import com.kaixue.domain.course.response.CategoryNodeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "课程分类管理",description = "课程分类管理",tags = {"课程分类管理"})
public interface CategoryControllerApi
{
    @ApiOperation("查询分类")
    CategoryNodeResponse findAll();
}
