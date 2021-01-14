package com.kaixue.manage_course;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.sql.DataSource;
import java.sql.SQLException;


//加载ApplicationContext，启动spring容器。
@SpringBootTest
//将Spring和Junit连接起来
@RunWith(SpringRunner.class)
public class TestConnection
{
    @Autowired
    DataSource dataSource;

    @Test
    public void test() throws  InterruptedException
    {
        System.out.println(dataSource.getClass().getName());

        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println(druidDataSource.getMaxActive());
        System.out.println(druidDataSource.getInitialSize());
        for (int i = 0; i < 50; i++)
        {
            new Thread(()->{
                DruidPooledConnection connection = null;
                try
                {
                    connection = druidDataSource.getConnection();
                    Thread.sleep(1000);
                }
                catch (SQLException | InterruptedException throwables)
                {
                    throwables.printStackTrace();
                }finally
                {
                    try
                    {
                        connection.close();
                    }
                    catch (SQLException throwables)
                    {
                        throwables.printStackTrace();
                    }
                }
                System.out.println("aaaa"+druidDataSource.getActiveCount());
            }).start();
        }
        Thread.sleep(Integer.MAX_VALUE);
    }

}
