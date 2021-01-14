package com.kaixue.domain.course.response;


import com.kaixue.domain.course.TeachPlan;
import com.kaixue.model.response.ResponseResult;
import com.kaixue.model.response.ResultCode;
import lombok.Data;

@Data
public class TeachPlanResponse extends ResponseResult
{
    private TeachPlan teachPlan;

    public TeachPlanResponse(ResultCode resultCode, TeachPlan teachPlan)
    {
        super(resultCode);
        this.teachPlan = teachPlan;
    }
}
