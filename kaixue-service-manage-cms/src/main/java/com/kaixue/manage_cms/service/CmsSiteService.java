package com.kaixue.manage_cms.service;

import com.kaixue.domain.cms.CmsSite;
import com.kaixue.manage_cms.dao.CmsSiteRepository;
import com.kaixue.model.response.CommonCode;
import com.kaixue.model.response.QueryResponseResult;
import com.kaixue.model.response.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsSiteService
{
    @Autowired
    CmsSiteRepository cmsSiteRepository;

    public QueryResponseResult<CmsSite> findAll()
    {
        List<CmsSite> all = cmsSiteRepository.findAll();

        QueryResult<CmsSite> queryResult = new QueryResult<>();
        queryResult.setList(all);
        queryResult.setTotal(all.size());

        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
    }
}
