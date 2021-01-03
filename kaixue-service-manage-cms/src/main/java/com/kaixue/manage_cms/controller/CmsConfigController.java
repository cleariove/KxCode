package com.kaixue.manage_cms.controller;

import com.kaixue.api.cms.CmsConfigControllerApi;
import com.kaixue.domain.cms.CmsConfig;
import com.kaixue.manage_cms.service.CmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/config")
public class CmsConfigController implements CmsConfigControllerApi
{
    @Autowired
    CmsConfigService cmsConfigService;

    @Override
    @GetMapping("get/model/{id}")
    public CmsConfig getModel(@PathVariable("id") String id)
    {
        return cmsConfigService.getConfigById(id);
    }
}
