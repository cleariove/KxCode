package com.kaixue.manage_cms.dao;

import com.kaixue.domain.cms.CmsPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsPageRepository extends MongoRepository<CmsPage,String>
{
    //这个方法的方法名中查询条件的顺序就是方法参数的顺序，即方法名中第一个时pageName，那么方法参数第一个参数就时pageName，无关方法名
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName, String siteId, String pageWebPath);

}
