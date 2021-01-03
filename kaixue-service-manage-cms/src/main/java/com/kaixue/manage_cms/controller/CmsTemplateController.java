package com.kaixue.manage_cms.controller;

import com.kaixue.api.cms.CmsTemplateControllerApi;
import com.kaixue.domain.cms.CmsTemplate;
import com.kaixue.manage_cms.service.CmsTemplateService;
import com.kaixue.model.response.QueryResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/template")
public class CmsTemplateController implements CmsTemplateControllerApi
{
    @Autowired
    CmsTemplateService cmsTemplateService;

    @Override
    @GetMapping("/list/all")
    public QueryResponseResult<CmsTemplate> findAll()
    {
        return cmsTemplateService.findAll();
    }
}
