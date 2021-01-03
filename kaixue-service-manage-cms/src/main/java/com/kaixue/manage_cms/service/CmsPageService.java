package com.kaixue.manage_cms.service;


import com.kaixue.domain.cms.CmsPage;
import com.kaixue.domain.cms.CmsTemplate;
import com.kaixue.domain.cms.request.QueryPageRequest;
import com.kaixue.domain.cms.response.CmsCode;
import com.kaixue.domain.cms.response.CmsPageResponse;
import com.kaixue.manage_cms.dao.CmsPageRepository;
import com.kaixue.manage_cms.dao.CmsTemplateRepository;
import com.kaixue.model.exception.ExceptionCast;
import com.kaixue.model.response.CommonCode;
import com.kaixue.model.response.QueryResponseResult;
import com.kaixue.model.response.QueryResult;
import com.kaixue.model.response.ResponseResult;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

@Service
public class CmsPageService
{
    @Autowired
    CmsPageRepository cmsPageRepository;

    @Autowired
    CmsTemplateRepository cmsTemplateRepository;

    //springboot提供了这个对象
    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    //main方法中注入
    @Autowired
    RestTemplate restTemplate;

    /**
     * cms页面分页查询
     * @param page 页码
     * @param size 页面显示数量
     * @param queryPageRequest 查询参数
     * @return Cms页面列表
     */
    public QueryResponseResult<CmsPage> findList(int page, int size, QueryPageRequest queryPageRequest)
    {
        //防止调用get方法时对象为空报错
        if (queryPageRequest == null)
            queryPageRequest = new QueryPageRequest();
        if (page <= 0)
            page = 1;
        page--;//前端从1计数，后端从0计数
        if (size <= 0)
            size = 20;
        //构建分页参数
        Pageable pageable = PageRequest.of(page,size);

        //构建查询条件
        CmsPage cmsPage = new CmsPage();
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId()))
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId()))
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase()))
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        if (StringUtils.isNotEmpty(queryPageRequest.getPageName()))
            cmsPage.setPageName(queryPageRequest.getPageName());
        if (StringUtils.isNotEmpty(queryPageRequest.getPageType()))
            cmsPage.setPageType(queryPageRequest.getPageType());

        //定义条件匹配器,这种查询方式为qbe，https://blog.csdn.net/weixin_40326107/article/details/106055871
        //因为往mongodb中插入时会自带全限定类名作为_class字段插入，而且在查询时也会默认带上这个条件，但是这个条件在业务逻辑中不需要用
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("_class")
                .withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("pageName", ExampleMatcher.GenericPropertyMatchers.contains());

        //定义条件对象
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);

        //从mongodb中查询数据
        Page<CmsPage> pages = cmsPageRepository.findAll(example,pageable);
        //将数据转换为响应格式
        QueryResult<CmsPage> cmsPageQueryResult = new QueryResult<>();
        cmsPageQueryResult.setList(pages.getContent());
        cmsPageQueryResult.setTotal(pages.getTotalElements());

        //构建返回值
        return new QueryResponseResult<>(CommonCode.SUCCESS,cmsPageQueryResult);
    }

    public CmsPageResponse add(CmsPage cmsPage)
    {
        if (cmsPage == null)
        {
            return new CmsPageResponse(CommonCode.FAIL,null);
        }
        if (this.isExistPage(cmsPage.getPageName(), cmsPage.getSiteId(),cmsPage.getPageWebPath()))
        {
            ExceptionCast.cast(CmsCode.CMC_PAGE_EXIST);
        }
        //主键要自动生成，这一行代码可以保底
        cmsPage.setPageId(null);
        cmsPageRepository.save(cmsPage);

        return new CmsPageResponse(CommonCode.SUCCESS,cmsPage);
    }

    public CmsPageResponse findById(String id)
    {
        //optional用于可以规范非空判断
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
//        这个返回代码等同于
//        if (!optional.isPresent())
//            return new CmsPageResponse(CommonCode.FAIL,null);
//        return new CmsPageResponse(CommonCode.SUCCESS,optional.get());
        return optional.map(cmsPage ->
                new CmsPageResponse(CommonCode.SUCCESS, cmsPage)).
                orElseGet(() -> new CmsPageResponse(CommonCode.FAIL, null));
    }

    public CmsPageResponse update(String id, CmsPage cmsPage)
    {
        //optional用于可以规范非空判断，orElse的参数表示对象为空时返回的参数
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        CmsPage originPage =  optional.orElse(null);
        if (originPage == null)
            return new CmsPageResponse(CommonCode.FAIL,null);
        //已经存在该页面
        if (this.isExistPage(cmsPage.getPageName(), cmsPage.getSiteId(),cmsPage.getPageWebPath(), cmsPage.getPageId()))
            return new CmsPageResponse(CmsCode.CMC_PAGE_EXIST,null);
        //更新所属模板
        originPage.setTemplateId(cmsPage.getTemplateId());
        //更新所属站点
        originPage.setSiteId(cmsPage.getSiteId());
        //更新页面别名
        originPage.setPageAliase(cmsPage.getPageAliase());
        //更新页面名称
        originPage.setPageName(cmsPage.getPageName());
        //更新访问路径
        originPage.setPageWebPath(cmsPage.getPageWebPath());
        //更新物理路径
        originPage.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
        //更新DataUrl
        originPage.setDataUrl(cmsPage.getDataUrl());
        CmsPage newPage = cmsPageRepository.save(originPage);
        if (newPage == null)
            return new CmsPageResponse(CommonCode.FAIL,null);
        return new CmsPageResponse(CommonCode.SUCCESS,newPage);
    }

    public ResponseResult delete(String id)
    {
        Optional<CmsPage> cmsPage = cmsPageRepository.findById(id);
        if (!cmsPage.isPresent())
            return new ResponseResult(CommonCode.FAIL);
        cmsPageRepository.deleteById(id);
        return new ResponseResult(CommonCode.SUCCESS);
    }


    /**
     * 获取页面的DataUrl，通过RestTemplate调用获取页面的数据
     *
     * 通过页面的templateId，获取页面的模板
     *
     * 执行页面静态化
     *
     * @param pageId
     * @return
     */
    //页面静态化方法
    public String getPageHtml(String pageId)
    {
        //获取页面数据
        Map model = this.getModelById(pageId);
        if (model == null)//数据模型为空时
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAISNULL);

        //获取页面模板信息
        String template = getTemplateById(pageId);
        if (StringUtils.isEmpty(template))//页面模板为空时
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);

        String html = generateHtml(template,model);
        return html;
    }

    //执行静态化
    private String generateHtml(String content,Map model)
    {
        //创建配置对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //创建模板加载器
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("template",content);
        //向configuration配置模板加载器
        configuration.setTemplateLoader(stringTemplateLoader);
        //获取模板
        try
        {
            Template template = configuration.getTemplate("template");
            //调用Api进行静态化处理
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
            return html;
        }
        catch (IOException | TemplateException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    //获取页面对应的模板信息
    private String getTemplateById(String pageId)
    {
        //获取CmsPage对象
        Optional<CmsPage> optional = this.cmsPageRepository.findById(pageId);
        if (!optional.isPresent())
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        CmsPage cmsPage = optional.get();
        //获取CmsTemplate对象
        String templateId = cmsPage.getTemplateId();
        if (StringUtils.isEmpty(templateId))
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        Optional<CmsTemplate> optional1 = cmsTemplateRepository.findById(templateId);
        if (!optional1.isPresent())
            return null;
        CmsTemplate cmsTemplate = optional1.get();
        //获取模板文件Id（这个id是fs.files中的id）
        String templateFileId = cmsTemplate.getTemplateFileId();
        //获取对应的文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(templateFileId)));
        //获取文件下载流对象
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //打开文件下载流
        GridFsResource gridFsResource = new GridFsResource(gridFSFile,downloadStream);
        //获取流中的数据
        try
        {
            String content = IOUtils.toString(gridFsResource.getInputStream(), StandardCharsets.UTF_8);
            return content;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    //获取数据模型，即页面对应的数据
    private Map getModelById(String pageId)
    {
        //获取页面信息
        Optional<CmsPage> optional = this.cmsPageRepository.findById(pageId);
        if (!optional.isPresent())
            //页面不存在
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        CmsPage cmsPage = optional.get();
        //获取dataUrl
        String dataUrl = cmsPage.getDataUrl();
        if (StringUtils.isEmpty(dataUrl))
            //dataUrl为空
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);

        //通过RestTemplate请求dataUrl获取数据
        //这个dataUrl就是CmsConfigControllerApi的接口
        //CmsConfig中保存了数据
        ResponseEntity<Map> entity = restTemplate.getForEntity(dataUrl, Map.class);
        Map map = entity.getBody();
        return map;
    }

    private boolean isExistPage(String pageName,String siteId,String pageWebPath)
    {
        CmsPage exist = cmsPageRepository.
                findByPageNameAndSiteIdAndPageWebPath(pageName, siteId, pageWebPath);
        return exist != null;
    }

    private boolean isExistPage(String pageName, String siteId, String pageWebPath, String pageId)
    {
        CmsPage exist = cmsPageRepository.
                findByPageNameAndSiteIdAndPageWebPath(pageName, siteId, pageWebPath);
        if (exist == null)
            return false;
        return ! exist.getPageId().equals(pageId);
    }
}
