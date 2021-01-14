package com.kaixue.manage_cms.service;

import com.kaixue.domain.system.SysDictionary;
import com.kaixue.domain.system.response.SysDictionaryResponse;
import com.kaixue.manage_cms.dao.SysDictionaryRepository;
import com.kaixue.model.response.CommonCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDictionaryService
{
    @Autowired
    SysDictionaryRepository sysDictionaryRepository;

    public SysDictionaryResponse findByType(String type)
    {
        SysDictionary byDType = sysDictionaryRepository.findByDType(type);
        return new SysDictionaryResponse(CommonCode.SUCCESS,byDType);
    }
}
