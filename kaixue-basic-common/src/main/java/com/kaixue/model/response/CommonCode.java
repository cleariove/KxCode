package com.kaixue.model.response;

import lombok.ToString;

/**
 * @Description:通用的返回状态码
 */
//以下注解用于自动生成get，set，equals等方法，属于lombok插件
@ToString
public enum CommonCode implements ResultCode
{
    //enum对象类型必须定义在类最开头
    INVALID_PARAM(false,10003,"非法参数！"),
    SUCCESS(true,10000,"操作成功！"),
    FAIL(false,11111,"操作失败！"),
    UNAUTHENTICATED(false,10001,"此操作需要登录系统！"),
    UNAUTHORISED(false,10002,"权限不足，无法操作！"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后再试！");

    //操作是否成功，默认成功
    boolean success;
    //操作返回码
    int code;
    //操作提示信息
    String msg;

    CommonCode(boolean success, int code, String msg)//enum对象的构造方法均为private
    {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public boolean success()
    {
        return success;
    }

    @Override
    public int code()
    {
        return code;
    }

    @Override
    public String message()
    {
        return msg;
    }
}
