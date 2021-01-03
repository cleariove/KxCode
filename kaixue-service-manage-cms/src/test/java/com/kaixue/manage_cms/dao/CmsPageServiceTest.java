package com.kaixue.manage_cms.dao;

import com.kaixue.manage_cms.service.CmsPageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//加载ApplicationContext，启动spring容器。
@SpringBootTest
//将Spring和Junit连接起来
@RunWith(SpringRunner.class)
public class CmsPageServiceTest
{
    @Autowired
    CmsPageService cmsPageService;

    @Test
    public void testGetHtml()
    {
        String pageHtml = cmsPageService.getPageHtml("5ff16f7b3aa3fc33c8a492e3");
        System.out.println(pageHtml);
    }
}
