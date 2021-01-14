package com.kaixue.manage_course;


import com.kaixue.domain.course.ext.TeachPlanNode;
import com.kaixue.manage_course.dao.TeachPlanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//加载ApplicationContext，启动spring容器。
@SpringBootTest
//将Spring和Junit连接起来
@RunWith(SpringRunner.class)
public class TestTeachPlanMapper
{
    @Autowired
    TeachPlanMapper teachPlanMapper;

    @Test
    public void testFindList()
    {
        TeachPlanNode list = teachPlanMapper.findList("4028e581617f945f01617f9dabc40000");
        System.out.println(list);
    }


}
