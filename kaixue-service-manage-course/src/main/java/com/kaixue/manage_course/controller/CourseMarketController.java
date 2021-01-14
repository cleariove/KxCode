package com.kaixue.manage_course.controller;

import com.kaixue.api.course.CourseMarketControllerApi;
import com.kaixue.domain.course.CourseMarket;
import com.kaixue.domain.course.response.CourseMarketResponse;
import com.kaixue.manage_course.service.CourseMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coursemarket")
public class CourseMarketController implements CourseMarketControllerApi
{
    @Autowired
    CourseMarketService courseMarketService;

    @Override
    @GetMapping("/get/{id}")
    public CourseMarketResponse findByCourseId(@PathVariable("id") String courseId)
    {
        return courseMarketService.findByCourseId(courseId);
    }

    @Override
    @PutMapping("/edit/{id}")
    public CourseMarketResponse updateCourseMarket(@PathVariable("id") String id,
                                                   @RequestBody CourseMarket courseMarket)
    {
        return courseMarketService.updateCourseMarket(id,courseMarket);
    }
}
