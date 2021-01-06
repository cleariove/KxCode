package com.kaixue.manage_cms_client.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig
{
    //了解spring配置文件https://zhuanlan.zhihu.com/p/77175198
    //队列名称,在application.yml中定义
    @Value("${kaixue.mq.queue}")
    public String queueName;
    //绑定的routingKey，这个routingKey也是站点id，表示发送到哪个站点页面
    @Value("${kaixue.mq.routing-key}")
    public String routingKey;
    //队列返回的bean的名称
    public static final String QUEUE_CMS_POST_PAGE = "queue_cms_post_page";
    //交换机的名称
    public static final String EX_ROUTING_CMS_POST_PAGE = "ex_routing_cms_post_page";

    @Bean
    public Binding binding(@Qualifier(QUEUE_CMS_POST_PAGE) Queue queue,//@Qualifier按照名称注入
                           @Qualifier(EX_ROUTING_CMS_POST_PAGE) Exchange exchange)
    {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
    }

    // 声明交换机
    // 关于RabbitMQ的工作模式
    // https://www.cnblogs.com/blacksmith4/p/13407456.html
    @Bean(EX_ROUTING_CMS_POST_PAGE)//注入的bean的名称
    public Exchange getExchange()
    {
        //返回的路由工作模式，并且持久化
        return ExchangeBuilder.directExchange(EX_ROUTING_CMS_POST_PAGE).durable(true).build();
    }

    //声明队列
    @Bean(QUEUE_CMS_POST_PAGE)
    public Queue getQueue()
    {
        Queue queue = new Queue(queueName);
        return queue;
    }


}
