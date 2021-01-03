package com.kaixue.model.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

//以下两个注解用于自动生成get，set，equals等方法，属于lombok插件
@Data
@ToString
public class QueryResult<T>
{
    //数据列表
    private List<T> list;
    //数据总数
    private long total;
}
