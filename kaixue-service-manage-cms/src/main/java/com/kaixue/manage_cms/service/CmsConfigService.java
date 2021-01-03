package com.kaixue.manage_cms.service;

import com.kaixue.domain.cms.CmsConfig;
import com.kaixue.manage_cms.dao.CmsConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CmsConfigService
{
    @Autowired
    CmsConfigRepository cmsConfigRepository;

    //根据id查询cmsConfig
    public CmsConfig getConfigById(String id)
    {
        Optional<CmsConfig> optional = cmsConfigRepository.findById(id);
        return optional.orElse(null);
    }
}
