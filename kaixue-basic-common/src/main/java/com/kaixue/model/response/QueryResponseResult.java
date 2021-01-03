package com.kaixue.model.response;

import lombok.Data;
import lombok.ToString;

//以下两个注解用于自动生成get，set，equals等方法，属于lombok插件
@Data
@ToString
public class QueryResponseResult<T> extends ResponseResult
{
    QueryResult<T> queryResult;

    public QueryResponseResult(ResultCode resultCode,QueryResult<T> queryResult)
    {
        super(resultCode);
        this.queryResult = queryResult;
    }
}
