package com.kaixue.api.course;

import com.kaixue.domain.course.CourseBase;
import com.kaixue.domain.course.TeachPlan;
import com.kaixue.domain.course.ext.CourseInfo;
import com.kaixue.domain.course.ext.TeachPlanNode;
import com.kaixue.domain.course.request.QueryCourseRequest;
import com.kaixue.domain.course.response.CourseResponse;
import com.kaixue.domain.course.response.TeachPlanNodeResponse;
import com.kaixue.domain.course.response.TeachPlanResponse;
import com.kaixue.model.response.QueryResponseResult;
import com.kaixue.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//api开头的注解均是swagger插件的注解，用于自动生成api文档
//swagger用法可以参照https://blog.csdn.net/wyb880501/article/details/79576784
@Api(value = "课程页面管理接口",tags = {"提供课程的增删改查"})
public interface CourseControllerApi
{
    @ApiOperation(value = "查询课程计划")
    TeachPlanNodeResponse findTeachPlanList(String courseId);

    @ApiOperation(value = "添加课程计划")
    TeachPlanResponse addTeachPlan(TeachPlan teachPlan);

    @ApiOperation("查询我的课程")
    QueryResponseResult<CourseInfo> findAllCourse(int page, int size, QueryCourseRequest queryCourseRequest);

    @ApiOperation("新增课程")
    CourseResponse addCourse(CourseBase courseBase);

    @ApiOperation("根据id查找课程")
    CourseResponse findCourseById(String courseId);

    @ApiOperation("修改课程信息")
    CourseResponse edit(String id, CourseBase courseBase);
}
