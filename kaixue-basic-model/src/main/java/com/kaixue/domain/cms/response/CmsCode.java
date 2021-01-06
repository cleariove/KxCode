package com.kaixue.domain.cms.response;

import com.kaixue.model.response.ResultCode;
import lombok.ToString;

//以下注解用于自动生成get，set，equals等方法，属于lombok插件
@ToString
public enum  CmsCode implements ResultCode
{
    CMC_PAGE_EXIST(false, 24001, "页面已经存在！"),
    CMS_GENERATEHTML_DATAURLISNULL(false,24002,"从页面信息中找不到获取数据的url！"),
    CMS_GENERATEHTML_DATAISNULL(false,24003,"根据页面的数据url获取不到数据！"),
    CMS_GENERATEHTML_TEMPLATEISNULL(false,24004,"页面模板为空！"),
    CMS_GENERATEHTML_HTMLISNULL(false,24005,"生成的静态html为空！"),
    CMS_GENERATEHTML_SAVEHTMLERROR(false,24005,"保存静态html出错！"),
    CMS_PAGE_NOTEXISTS(false,24006,"页面不存在"),
    CMS_COURSE_PERVIEWISNULL(false,24007,"预览页面为空！"),
    CMS_SITE_NOT_EXISTS(false, 24008,"站点不存在"),
    CMS_MONGO_FILE_IS_NULL(false,24009,"获取不到文件");

    boolean success;

    int code;

    String message;

    CmsCode(boolean success, int code, String message)
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
