package com.kaixue.manage_cms_client.service;

import com.kaixue.domain.cms.CmsPage;
import com.kaixue.domain.cms.CmsSite;
import com.kaixue.domain.cms.response.CmsCode;
import com.kaixue.manage_cms_client.dao.CmsPageRepository;
import com.kaixue.manage_cms_client.dao.CmsSiteRepository;
import com.kaixue.model.exception.ExceptionCast;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class CmsPageService
{
    @Autowired
    CmsPageRepository cmsPageRepository;

    @Autowired
    CmsSiteRepository cmsSiteRepository;

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsPageService.class);

    //将页面从MongoDB中下载保存至服务器路径下
    public void savePageToServerPath(String pageId)
    {
        //获得页面信息
        CmsPage cmsPage = this.findByPageId(pageId);
        //得到html文件的id，从cms中获取htmlFileId内容
        String htmlFileId = cmsPage.getHtmlFileId();

        //获取文件的输入流
        InputStream is = this.getFileById(htmlFileId);
        if (is == null)
        {
            LOGGER.error("获取文件输出流失败，htmlFileId:{}",htmlFileId);
            return ;
        }
        //获取站点Id
        String siteId = cmsPage.getSiteId();
        CmsSite cmsSite = this.findBySiteId(siteId);
        String physicalPath = cmsSite.getSitePhysicalPath();
        //拼接成服务器上文件的路径
        String pagePath = physicalPath+cmsPage.getPagePhysicalPath()+cmsPage.getPageName();
        try(FileOutputStream fos = new FileOutputStream(pagePath))
        {
            IOUtils.copy(is,fos);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    private InputStream getFileById(String fileId)
    {
        //获取文件对象
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
        if (gridFSFile == null)
            return null;
        //下载流
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //
        GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFSDownloadStream);
        try
        {
            return gridFsResource.getInputStream();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private CmsPage findByPageId(String pageId)
    {
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if (!optional.isPresent())
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
         return optional.get();
    }

    private CmsSite findBySiteId(String siteId)
    {
        Optional<CmsSite> optional = cmsSiteRepository.findById(siteId);
        if (!optional.isPresent())
            ExceptionCast.cast(CmsCode.CMS_SITE_NOT_EXISTS);
        return optional.get();
    }
}
