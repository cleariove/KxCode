package com.kaixue.manage_course.dao;

import com.github.pagehelper.Page;
import com.kaixue.domain.course.ext.CourseInfo;
import com.kaixue.domain.course.request.QueryCourseRequest;
import org.apache.ibatis.annotations.Mapper;

// @Mapper,@Repository,@MapperScan的区别 https://blog.csdn.net/Xu_JL1997/article/details/90934359
@Mapper
public interface CourseBaseMapper
{
    Page<CourseInfo> findCourseList(QueryCourseRequest queryCourseRequest);
}
