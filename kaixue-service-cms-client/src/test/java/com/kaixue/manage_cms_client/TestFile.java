package com.kaixue.manage_cms_client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;

//加载ApplicationContext，启动spring容器。
@SpringBootTest
//将Spring和Junit连接起来
@RunWith(SpringRunner.class)
public class TestFile
{
    @Test
    public void fileWrite()
    {
        File file = new File("D:\\OnlineCourse\\KxUI\\kx-ui-pc-static-portal\\测试1\\测试页面静态化.html");
        System.out.println(file.exists());
        //        FileOutputStream fileOutputStream = new FileOutputStream("D:\\OnlineCourse\\KxUI\\kx-ui-pc-static-portal\\测试1\\测试页面静态化.html");
//        fileOutputStream.
    }
}
