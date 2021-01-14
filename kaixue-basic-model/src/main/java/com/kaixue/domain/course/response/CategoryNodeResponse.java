package com.kaixue.domain.course.response;

import com.kaixue.domain.course.ext.CategoryNode;
import com.kaixue.model.response.ResponseResult;
import com.kaixue.model.response.ResultCode;
import lombok.Data;

@Data
public class CategoryNodeResponse extends ResponseResult
{
    private CategoryNode categoryNode;

    public CategoryNodeResponse(ResultCode resultCode,CategoryNode categoryNode)
    {
        super(resultCode);
        this.categoryNode = categoryNode;
    }
}
