package com.kaixue.domain.course.ext;

import com.kaixue.domain.course.TeachPlan;
import lombok.Data;
import lombok.ToString;

import java.util.List;

//以下两个注解用于自动生成get，set，equals等方法，属于lombok插件
@Data
@ToString
public class TeachPlanNode extends TeachPlan
{
    private List<TeachPlan> children;

}
