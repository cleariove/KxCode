package com.kaixue.model.exception;

import com.kaixue.model.response.ResultCode;

/**
 * 自定义的异常类型
 * https://blog.csdn.net/weixin_38399962/article/details/79582569
 */
public class CustomException extends RuntimeException
{
    final ResultCode resultCode;

    public CustomException(ResultCode resultCode)
    {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode()
    {
        return resultCode;
    }
}
