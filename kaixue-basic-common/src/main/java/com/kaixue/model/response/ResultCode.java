package com.kaixue.model.response;

public interface ResultCode
{
    //操作是否成功，true为成功
    boolean success();
    //操作响应代码
    int code();
    //操作响应信息
    String message();
}
