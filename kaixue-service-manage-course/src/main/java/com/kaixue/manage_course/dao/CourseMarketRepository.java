package com.kaixue.manage_course.dao;

import com.kaixue.domain.course.CourseMarket;
import org.springframework.data.jpa.repository.JpaRepository;

// 继承了Repository接口就等于注解了@Repository
public interface CourseMarketRepository extends JpaRepository<CourseMarket,String>
{
}
