package com.kaixue.manage_cms.controller;


import com.kaixue.api.cms.CmsPageControllerApi;
import com.kaixue.domain.cms.CmsPage;
import com.kaixue.domain.cms.request.QueryPageRequest;
import com.kaixue.domain.cms.response.CmsPageResponse;
import com.kaixue.manage_cms.service.CmsPageService;
import com.kaixue.model.response.QueryResponseResult;
import com.kaixue.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi
{
    @Autowired
    CmsPageService cmsPageService;

    @Override
    @GetMapping("/list/{page}/{size}")
    // QueryPageRequest没有加任何注解，且在参数列表上
    // 等同于将这个对象的各个字段默认连接在url中参数部分，并且可以不填
    // QueryPageRequest这个参数作用，和QueryPageRequest全加上以下效果相同
    // @RequestParam(required = false,name = "siteId") String siteId
    // @RequestParam(required = false,name = "pageId") String pageId
    // SpringMvc接受参数的方法详见https://www.bbsmax.com/A/Ae5RRVqM5Q/
    public QueryResponseResult<CmsPage> findList(@PathVariable("page") int page,
                                        @PathVariable("size") int size,
                                        QueryPageRequest queryPageRequest)
    {
        return cmsPageService.findList(page,size,queryPageRequest);
//        List<CmsPage> list = new ArrayList<>();
//        CmsPage cmsPage = new CmsPage();
//        cmsPage.setPageName("测试");
//        list.add(cmsPage);
//        QueryResult<CmsPage> queryResult = new QueryResult<>();
//        queryResult.setTotal(1);
//        queryResult.setData(list);
//        QueryResponseResult<CmsPage> result = new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
//        return result;
    }

    @Override
    @PostMapping("/add")
    public CmsPageResponse add(@RequestBody CmsPage cmsPage)
    {
        return cmsPageService.add(cmsPage);
    }

    @Override
    @GetMapping("/get/{id}")
    public CmsPageResponse findById(@PathVariable("id") String id)
    {
        return cmsPageService.findById(id);
    }

    @Override
    @PutMapping("/edit/{id}")//put表示更新
    public CmsPageResponse edit(@PathVariable("id") String id,@RequestBody CmsPage cmsPage)
    {
        return cmsPageService.update(id,cmsPage);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable("id") String id)
    {
        return cmsPageService.delete(id);
    }

    @Override
    @PostMapping("/post/{pageId}")
    public ResponseResult post(@PathVariable("pageId") String pageId)
    {
        return cmsPageService.postPage(pageId);
    }
}
