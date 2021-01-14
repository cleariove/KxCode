package com.kaixue.manage_course.dao;

import com.kaixue.domain.course.TeachPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachPlanRepository extends JpaRepository<TeachPlan, String>
{
    //定义方法根据课程id和父节点id查询节点列表
    List<TeachPlan> findByCourseIdAndParentId(String courseId,String parentId);
}
