package com.kaixue.model.response;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//以下三个注解用于自动生成get，set，equals等方法，属于lombok插件
@Data
@ToString
@NoArgsConstructor
public class ResponseResult implements Response
{
    //操作是否成功，默认成功
    boolean success = SUCCESS;
    //操作返回码
    int code = SUCCESS_CODE;
    //操作提示信息
    String msg;

    public ResponseResult(ResultCode resultCode)
    {
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.msg = resultCode.message();
    }

    public static ResponseResult SUCCESS()
    {
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public static ResponseResult FAIL()
    {
        return new ResponseResult(CommonCode.FAIL);
    }
}
