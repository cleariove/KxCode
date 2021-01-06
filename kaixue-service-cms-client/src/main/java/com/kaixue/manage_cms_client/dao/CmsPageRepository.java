package com.kaixue.manage_cms_client.dao;

import com.kaixue.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsPageRepository extends MongoRepository<CmsPage,String>
{
}
