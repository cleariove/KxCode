package com.kaixue.domain.course.response;

import com.kaixue.domain.course.TeachPlan;
import com.kaixue.domain.course.ext.TeachPlanNode;
import com.kaixue.model.response.ResponseResult;
import com.kaixue.model.response.ResultCode;
import lombok.Data;

@Data
public class TeachPlanNodeResponse extends ResponseResult
{

    private TeachPlanNode teachPlanNode;

    public TeachPlanNodeResponse(ResultCode resultCode, TeachPlanNode teachPlanNode)
    {
        super(resultCode);
        this.teachPlanNode = teachPlanNode;
    }
}
