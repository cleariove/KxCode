package com.kaixue.manage_course.controller;

import com.kaixue.api.course.CourseControllerApi;
import com.kaixue.domain.course.CourseBase;
import com.kaixue.domain.course.TeachPlan;
import com.kaixue.domain.course.ext.CourseInfo;
import com.kaixue.domain.course.request.QueryCourseRequest;
import com.kaixue.domain.course.response.CourseResponse;
import com.kaixue.domain.course.response.TeachPlanNodeResponse;
import com.kaixue.domain.course.response.TeachPlanResponse;
import com.kaixue.manage_course.service.CourseService;
import com.kaixue.model.response.QueryResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/course")
public class CourseController implements CourseControllerApi
{
    @Autowired
    CourseService courseService;

    @Override
    @GetMapping("/teachplan/list/{id}")
    public TeachPlanNodeResponse findTeachPlanList(@PathVariable("id") String courseId)
    {
        return courseService.findList(courseId);
    }

    @Override
    @PostMapping("/teachplan/add")
    public TeachPlanResponse addTeachPlan(@RequestBody TeachPlan teachPlan)
    {
        return courseService.addTeachPlan(teachPlan);
    }

    @Override
    @GetMapping("/coursebase/list/{page}/{size}")
    public QueryResponseResult<CourseInfo> findAllCourse(@PathVariable("page") int page,
                                                         @PathVariable("size") int size,
                                                         QueryCourseRequest queryCourseRequest)
    {
        return courseService.findCourseList(page,size,queryCourseRequest);
    }

    @Override
    @PostMapping("/coursebase/add")
    public CourseResponse addCourse(@RequestBody CourseBase courseBase)
    {
        return courseService.addCourse(courseBase);
    }

    @Override
    @GetMapping("/coursebase/get/{id}")
    public CourseResponse findCourseById(@PathVariable("id") String courseId)
    {
        return courseService.findById(courseId);
    }

    @Override
    @PutMapping("/coursebase/edit/{id}")
    public CourseResponse edit(@PathVariable("id") String id,
                               @RequestBody CourseBase courseBase)
    {
        return courseService.update(id,courseBase);
    }
}
