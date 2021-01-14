package com.kaixue.manage_course.dao;

import com.kaixue.domain.course.TeachPlan;
import com.kaixue.domain.course.ext.TeachPlanNode;
import org.apache.ibatis.annotations.Mapper;

// @Mapper属于mybatis的注解
// @Mapper,@Repository,@MapperScan的区别 https://blog.csdn.net/Xu_JL1997/article/details/90934359
// 简单的整合教程 https://blog.csdn.net/u012702547/article/details/88643598
// mapper.xml的编写官网 https://mybatis.org/mybatis-3/configuration.html
@Mapper
public interface TeachPlanMapper
{
    TeachPlanNode findList(String courseId);
}
