package com.kaixue.manage_cms.controller;

import com.kaixue.api.cms.CmsSiteControllerApi;
import com.kaixue.domain.cms.CmsSite;
import com.kaixue.manage_cms.service.CmsSiteService;
import com.kaixue.model.response.QueryResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/site")
public class CmsSiteController implements CmsSiteControllerApi
{
    @Autowired
    CmsSiteService cmsSiteService;

    @Override
    @GetMapping("/list/all")
    public QueryResponseResult<CmsSite> findAll()
    {
        return cmsSiteService.findAll();
    }
}
