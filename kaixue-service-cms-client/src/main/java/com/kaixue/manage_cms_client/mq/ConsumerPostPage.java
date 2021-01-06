package com.kaixue.manage_cms_client.mq;

import com.alibaba.fastjson.JSON;
import com.kaixue.domain.cms.CmsPage;
import com.kaixue.manage_cms_client.dao.CmsPageRepository;
import com.kaixue.manage_cms_client.service.CmsPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class ConsumerPostPage
{
    @Autowired
    CmsPageRepository cmsPageRepository;

    @Autowired
    CmsPageService cmsPageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerPostPage.class);

    //表示监听的队列，这个注解的使用可以参考官方文档
    //https://docs.spring.io/spring-amqp/docs/current/reference/html/#async-annotation-driven
    @RabbitListener(queues = {"${kaixue.mq.queue}"})
    public void postPage(String msg)
    {
        Map map = JSON.parseObject(msg, Map.class);
        String pageId = (String) map.get("pageId");
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if (!optional.isPresent())
        {
            LOGGER.error("消息中的PageId为空{}", msg);
            return ;
        }
        //将文件从mongodb中下载至服务器上
        cmsPageService.savePageToServerPath(pageId);
    }
}
