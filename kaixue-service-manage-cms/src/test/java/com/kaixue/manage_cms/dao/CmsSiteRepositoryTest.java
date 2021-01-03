package com.kaixue.manage_cms.dao;

import com.kaixue.domain.cms.CmsSite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//加载ApplicationContext，启动spring容器。
@SpringBootTest
//将Spring和Junit连接起来
@RunWith(SpringRunner.class)
public class CmsSiteRepositoryTest
{
    @Autowired
    CmsSiteRepository cmsSiteRepository;

    @Test
    public void testFindAll()
    {
        List<CmsSite> all = cmsSiteRepository.findAll();
        System.out.println(all);
    }
}
