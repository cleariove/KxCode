package com.kaixue.api.course;


import com.kaixue.domain.course.CourseMarket;
import com.kaixue.domain.course.response.CourseMarketResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "课程营销管理接口",tags = {"提供课程营销的增删改查"})
public interface CourseMarketControllerApi
{
    @ApiOperation("获取课程营销信息")
    CourseMarketResponse findByCourseId(String courseId);

    @ApiOperation("更新课程营销信息")
    CourseMarketResponse updateCourseMarket(String id, CourseMarket courseMarket);
}
