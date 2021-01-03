package com.kaixue.manage_cms.dao;

import com.kaixue.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsTemplateRepository extends MongoRepository<CmsTemplate,String>
{

}
