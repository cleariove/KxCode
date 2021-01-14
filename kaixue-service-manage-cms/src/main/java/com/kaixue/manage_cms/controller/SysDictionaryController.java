package com.kaixue.manage_cms.controller;

import com.kaixue.api.sys.SysDictionaryControllerApi;
import com.kaixue.domain.system.response.SysDictionaryResponse;
import com.kaixue.manage_cms.service.SysDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/dictionary")
public class SysDictionaryController implements SysDictionaryControllerApi
{
    @Autowired
    SysDictionaryService sysDictionaryService;

    @Override
    @GetMapping("/get/{dType}")
    public SysDictionaryResponse findByType(@PathVariable("dType") String type)
    {
        return sysDictionaryService.findByType(type);
    }
}
