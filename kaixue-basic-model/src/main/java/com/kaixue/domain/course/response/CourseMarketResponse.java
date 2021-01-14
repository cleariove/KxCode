package com.kaixue.domain.course.response;

import com.kaixue.domain.course.CourseMarket;
import com.kaixue.model.response.ResponseResult;
import com.kaixue.model.response.ResultCode;
import lombok.Data;

@Data
public class CourseMarketResponse extends ResponseResult
{
    private CourseMarket courseMarket;

    public CourseMarketResponse(ResultCode resultCode,CourseMarket courseMarket)
    {
        super(resultCode);
        this.courseMarket = courseMarket;
    }
}
