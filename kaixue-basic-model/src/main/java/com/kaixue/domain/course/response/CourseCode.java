package com.kaixue.domain.course.response;

import com.kaixue.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;

public enum CourseCode implements ResultCode
{
    COURSE_DENIED_DELETE(false,31001,"删除课程失败，只允许删除本机构的课程！"),
    COURSE_PUBLISH_PERVIEWISNULL(false,31002,"还没有进行课程预览！"),
    COURSE_PUBLISH_CDETAILERROR(false,31003,"创建课程详情页面出错！"),
    COURSE_PUBLISH_COURSEIDISNULL(false,31004,"课程Id为空！"),
    COURSE_PUBLISH_VIEWERROR(false,31005,"发布课程视图出错！"),
    COURSE_NOT_EXIST(false,31006,"课程不存在！"),
    COURSE_MEDIS_URLISNULL(false,31101,"选择的媒资文件访问地址为空！"),
    COURSE_MEDIS_NAMEISNULL(false,31102,"选择的媒资文件名称为空！"),
    COURSE_ROOT_PLAN_IS_NULL(false,31201,"不存在父节点");

    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;
    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;
    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;

    CourseCode(boolean success, int code, String message)
    {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
