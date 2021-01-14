package com.kaixue.domain.system.response;

import com.kaixue.domain.system.SysDictionary;
import com.kaixue.model.response.ResponseResult;
import com.kaixue.model.response.ResultCode;
import lombok.Data;

@Data
public class SysDictionaryResponse extends ResponseResult
{
    private SysDictionary sysDictionary;

    public SysDictionaryResponse(ResultCode resultCode, SysDictionary sysDictionary)
    {
        super(resultCode);
        this.sysDictionary = sysDictionary;
    }
}
