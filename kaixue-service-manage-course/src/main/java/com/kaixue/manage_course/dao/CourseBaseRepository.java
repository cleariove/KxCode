package com.kaixue.manage_course.dao;

import com.kaixue.domain.course.CourseBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseBaseRepository extends JpaRepository<CourseBase,String>
{
}
