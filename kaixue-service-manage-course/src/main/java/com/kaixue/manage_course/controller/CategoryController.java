package com.kaixue.manage_course.controller;

import com.kaixue.api.course.CategoryControllerApi;
import com.kaixue.domain.course.response.CategoryNodeResponse;
import com.kaixue.manage_course.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController implements CategoryControllerApi
{
    @Autowired
    CategoryService categoryService;

    @Override
    @GetMapping("/list")
    public CategoryNodeResponse findAll()
    {
        return categoryService.findAll();
    }
}
