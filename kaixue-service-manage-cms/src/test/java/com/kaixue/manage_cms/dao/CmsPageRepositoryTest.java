package com.kaixue.manage_cms.dao;


import com.kaixue.domain.cms.CmsPage;
import com.kaixue.domain.cms.CmsPageParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//加载ApplicationContext，启动spring容器。
@SpringBootTest
//将Spring和Junit连接起来
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest
{
    @Autowired
    CmsPageRepository cmsPageRepository;

    @Test
    public void testFindAll()
    {
        List<CmsPage> all = cmsPageRepository.findAll();
        System.out.println(all);
    }

    @Test
    public void testFind()
    {
        CmsPage page = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath("123", "5a751fab6abb5044e0d19ea1", "123");
        System.out.println(page);
    }

    @Test
    public void testFindPage()
    {
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page,size);
        Page<CmsPage> pages = cmsPageRepository.findAll(pageable);
        System.out.println(pages);
    }

    @Test
    public void testInsert()
    {
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("s01");
        cmsPage.setTemplateId("t01");
        cmsPage.setPageName("测试页面");
        List<CmsPageParam> cmsPageParams = new ArrayList<>();
        CmsPageParam cmsPageParam = new CmsPageParam();
        cmsPageParam.setPageParamName("param");
        cmsPageParam.setPageParamValue("value");
        cmsPageParams.add(cmsPageParam);
        cmsPage.setPageParams(cmsPageParams);
        cmsPageRepository.save(cmsPage);
    }

    @Test
    public void testDelete()
    {
        cmsPageRepository.deleteById("5fe358e73aa3fc366498eff1");
    }

    @Test
    public void testUpdate()
    {
        // Optional是一个容器对象，它包括了我们需要的对象
        // 使用isPresent方法判断所包含对象是否为空
        // 规范了非空标准化检测
        Optional<CmsPage> optional = cmsPageRepository.findById("5fe358e73aa3fc366498eff1");
        if (optional.isPresent())
        {
            CmsPage cmsPage = optional.get();
            cmsPage.setPageName("12312");
            cmsPageRepository.save(cmsPage);
        }
    }

    @Test
    public void testFindAllByParams()
    {
        //构建条件匹配器,表明如何使用查询条件
        // 这中查询方法是使用qbe查询，
        // mongodb中qbe具体使用可以查看https://blog.csdn.net/weixin_40326107/article/details/106055871
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("_class")//构建匹配查询条件器
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());//构建页面别名的模糊匹配器
//        ExampleMatcher.GenericPropertyMatchers.contains();包含匹配器
//        ExampleMatcher.GenericPropertyMatchers.startsWith();开头匹配器
        //设定查询值
        CmsPage cmsPage = new CmsPage();
//        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
//        cmsPage.setTemplateId("5a925be7b00ffc4b3c1578b5");
        cmsPage.setPageAliase("课程");
        //查询条件实例，第一个参数表示查询构建对象，第二个参数表示查询的规则
        Example<CmsPage> example = Example.of(cmsPage,matcher);
        //分页参数
        Pageable pageable = PageRequest.of(0,10);

        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        List<CmsPage> content = all.getContent();
        System.out.println(content);
    }
    @Test
    public void testFindAllByExample() {
        //分页参数
        int page = 0;//从0开始
        int size = 10;
        Pageable pageable = PageRequest.of(page,size);

        //条件值对象
        CmsPage cmsPage= new CmsPage();
        //要查询5a751fab6abb5044e0d19ea1站点的页面
//        cmsPage.setSiteId("5b30b052f58b4411fc6cb1cf");
        //设置模板id条件
//        cmsPage.setTemplateId("5ad9a24d68db5239b8fef199");
        //设置页面别名
        cmsPage.setPageAliase("轮播");
        //条件匹配器
//        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//        exampleMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        //ExampleMatcher.GenericPropertyMatchers.contains() 包含关键字
//        ExampleMatcher.GenericPropertyMatchers.startsWith()//前缀匹配
        //定义Example
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        List<CmsPage> content = all.getContent();
        System.out.println(content);
    }
}
