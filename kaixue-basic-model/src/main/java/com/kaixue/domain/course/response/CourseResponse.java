package com.kaixue.domain.course.response;

import com.kaixue.domain.course.CourseBase;
import com.kaixue.model.response.ResponseResult;
import com.kaixue.model.response.ResultCode;
import lombok.Data;

@Data
public class CourseResponse extends ResponseResult
{
    private CourseBase courseBase;

    public CourseResponse(ResultCode resultCode, CourseBase courseBase)
    {
        super(resultCode);
        this.courseBase = courseBase;
    }
}
