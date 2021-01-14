package com.kaixue.manage_course.service;

import com.kaixue.domain.course.CourseMarket;
import com.kaixue.domain.course.response.CourseMarketResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCourseMarketService
{
    @Autowired
    CourseMarketService courseMarketService;

    @Test
    public void testFindById()
    {
        CourseMarketResponse byCourseId = courseMarketService.findByCourseId("297e7c7c62b888f00162b8a7dec20000");
        System.out.println(byCourseId);
    }

    @Test
    public void testAddCourseMarket() throws InterruptedException
    {
        String id = "40287d8176e639f90176e644abe90001";
        System.out.println(id.length());
        new Thread(()->{
            CourseMarket courseMarket = new CourseMarket();
            courseMarket.setCharge("1111");
            courseMarket.setValid("1111");
            courseMarket.setExpires(new Date());
            courseMarketService.updateCourseMarket(id,courseMarket);
        }).start();
        new Thread(()->{
            CourseMarket courseMarket = new CourseMarket();
            courseMarket.setCharge("aaaa");
            courseMarket.setValid("aaaa");
            courseMarket.setExpires(new Date());
            courseMarketService.updateCourseMarket(id,courseMarket);
        }).start();
        Thread.sleep(8000);
    }
}
