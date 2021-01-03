package com.kaixue.manage_cms.service;

import com.kaixue.domain.cms.CmsTemplate;
import com.kaixue.manage_cms.dao.CmsTemplateRepository;
import com.kaixue.model.response.CommonCode;
import com.kaixue.model.response.QueryResponseResult;
import com.kaixue.model.response.QueryResult;
import com.kaixue.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsTemplateService
{
    @Autowired
    CmsTemplateRepository cmsTemplateRepository;

    public QueryResponseResult<CmsTemplate> findAll()
    {
        List<CmsTemplate> all = cmsTemplateRepository.findAll();

        QueryResult<CmsTemplate> queryResult = new QueryResult<>();
        queryResult.setTotal(all.size());
        queryResult.setList(all);

        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }
}
