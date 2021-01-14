package com.kaixue.domain.course.request;

import com.kaixue.model.request.RequestData;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "course页面查询类", description = "course页面查询具体参数")
public class QueryCourseRequest extends RequestData
{

}
