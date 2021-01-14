package com.kaixue.manage_course.service;

import com.kaixue.domain.course.CourseBase;
import com.kaixue.domain.course.ext.CourseInfo;
import com.kaixue.domain.course.response.CourseResponse;
import com.kaixue.model.response.QueryResponseResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//加载ApplicationContext，启动spring容器。
@SpringBootTest
//将Spring和Junit连接起来
@RunWith(SpringRunner.class)
public class TestCourseService
{
    @Autowired
    CourseService courseService;

    @Test
    public void testFindCourseList()
    {
        QueryResponseResult<CourseInfo> courseList = courseService.findCourseList(0, 10, null);
        System.out.println(courseList);
    }

    @Test
    public void testFindById()
    {
        CourseResponse byId = courseService.findById("297e7c7c62b888f00162b8a7dec20000");
        System.out.println(byId);
    }

    @Test
    public void testUpdate()
    {
        CourseBase courseBase = new CourseBase();
        courseBase.setDescription("测试");
        CourseResponse update = courseService.update("297e7c7c62b888f00162b8a7dec20000", courseBase);
        System.out.println(update);
    }

}
