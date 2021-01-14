package com.kaixue.manage_course.service;

import com.kaixue.domain.course.ext.CategoryNode;
import com.kaixue.domain.course.response.CategoryNodeResponse;
import com.kaixue.manage_course.dao.CategoryMapper;
import com.kaixue.model.response.CommonCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService
{
    @Autowired
    CategoryMapper categoryMapper;

    public CategoryNodeResponse findAll()
    {
        CategoryNode all = categoryMapper.findAll();
        return new CategoryNodeResponse(CommonCode.SUCCESS,all);
    }
}
