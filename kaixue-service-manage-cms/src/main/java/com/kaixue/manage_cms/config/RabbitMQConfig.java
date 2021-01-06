package com.kaixue.manage_cms.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig
{
    public static final String EX_ROUTING_CMS_POST_PAGE = "ex_routing_cms_post_page";

    /**
     * 交换机配置使用direct
     * 消息发送方只需要确认哪个交换机接受数据即可
     * @return
     */
    @Bean(EX_ROUTING_CMS_POST_PAGE)
    public Exchange getExchange()
    {
        return ExchangeBuilder.directExchange(EX_ROUTING_CMS_POST_PAGE).durable(true).build();
    }
}
