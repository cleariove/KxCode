package com.kaixue.manage_cms.controller;

import com.kaixue.domain.cms.response.CmsCode;
import com.kaixue.manage_cms.service.CmsPageService;
import com.kaixue.model.exception.ExceptionCast;
import com.kaixue.model.web.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

@Controller
public class CmsPagePreviewController extends BaseController
{
    @Autowired
    CmsPageService cmsPageService;

    @GetMapping("/cms/preview/{id}")
    public void preview(@PathVariable("id") String pageId)
    {
        String html = cmsPageService.getPageHtml(pageId);
        if (StringUtils.isEmpty(html))
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        try
        {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(html.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
