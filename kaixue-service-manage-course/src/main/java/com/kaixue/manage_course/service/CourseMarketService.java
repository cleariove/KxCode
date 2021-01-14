package com.kaixue.manage_course.service;

import com.kaixue.domain.course.CourseMarket;
import com.kaixue.domain.course.response.CourseCode;
import com.kaixue.domain.course.response.CourseMarketResponse;
import com.kaixue.manage_course.dao.CourseMarketRepository;
import com.kaixue.model.exception.ExceptionCast;
import com.kaixue.model.response.CommonCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CourseMarketService
{
    @Autowired
    CourseMarketRepository courseMarketRepository;

    public CourseMarketResponse findByCourseId(String courseId)
    {
        Optional<CourseMarket> optional = courseMarketRepository.findById(courseId);
        return optional.map(courseMarket -> new CourseMarketResponse(CommonCode.SUCCESS, courseMarket)).
                orElseGet(() -> new CourseMarketResponse(CommonCode.SUCCESS, null));
    }


    // 假设两个线程同时执行该方法，发现都还没有营销信息，那就都会去创建营销信息，
    // 对于一个线程来说就会出现异常，那么这种情况如何处理，
    // 在test程序中有模板
    @Transactional
    public CourseMarketResponse updateCourseMarket(String id, CourseMarket courseMarket)
    {
        if (id == null
                || id.length() != 32
                || courseMarket == null
                || StringUtils.isEmpty(courseMarket.getCharge())
                || StringUtils.isEmpty(courseMarket.getValid())
                || courseMarket.getExpires() == null)
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        CourseMarketResponse response = this.findByCourseId(id);
        if (response.getCourseMarket() == null)
            courseMarket.setId(id);
        else
        {
            CourseMarket origin = response.getCourseMarket();
            courseMarket.setId(id);
            if (StringUtils.isEmpty(courseMarket.getQq()))
                courseMarket.setQq(origin.getQq());
            if (StringUtils.isEmpty(courseMarket.getCharge()))
                courseMarket.setCharge(origin.getCharge());
            if (StringUtils.isEmpty(courseMarket.getValid()))
                courseMarket.setValid(origin.getValid());
            if (courseMarket.getExpires() == null)
                courseMarket.setExpires(origin.getExpires());
            if (courseMarket.getPrice() == null)
                courseMarket.setPrice(origin.getPrice());
            if (courseMarket.getPriceOld() == null)
                courseMarket.setPriceOld(origin.getPriceOld());
            if (courseMarket.getStartTime() == null)
                courseMarket.setStartTime(origin.getStartTime());
            if (courseMarket.getEndTime() == null)
                courseMarket.setEndTime(origin.getEndTime());
        }
        courseMarket = courseMarketRepository.save(courseMarket);
        return new CourseMarketResponse(CommonCode.SUCCESS,courseMarket);
    }
}
