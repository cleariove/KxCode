package com.kaixue.domain.cms.response;

import com.kaixue.domain.cms.CmsPage;
import com.kaixue.model.response.ResponseResult;
import com.kaixue.model.response.ResultCode;
import lombok.Data;


//以下注解用于自动生成get，set，equals等方法，属于lombok插件
@Data
public class CmsPageResponse extends ResponseResult
{
    private CmsPage cmsPage;

    public CmsPageResponse(ResultCode resultCode, CmsPage cmsPage)
    {
        super(resultCode);
        this.cmsPage = cmsPage;
    }
}
