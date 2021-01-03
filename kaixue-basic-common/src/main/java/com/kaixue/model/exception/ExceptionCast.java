package com.kaixue.model.exception;


import com.kaixue.model.response.ResultCode;

/**
 * 这个类用于专门抛出异常
 */
public class ExceptionCast
{
    public static void cast(ResultCode resultCode)
    {
        throw new CustomException(resultCode);
    }
}
