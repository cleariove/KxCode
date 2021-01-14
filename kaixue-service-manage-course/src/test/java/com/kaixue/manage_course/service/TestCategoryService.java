package com.kaixue.manage_course.service;

import com.kaixue.domain.course.response.CategoryNodeResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCategoryService
{
    @Autowired
    CategoryService categoryService;

    @Test
    public void testFinaAll()
    {
        CategoryNodeResponse all = categoryService.findAll();
        System.out.println(all);
    }
}
