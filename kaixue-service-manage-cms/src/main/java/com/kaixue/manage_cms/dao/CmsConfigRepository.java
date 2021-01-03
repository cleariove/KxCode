package com.kaixue.manage_cms.dao;

import com.kaixue.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsConfigRepository extends MongoRepository<CmsConfig, String>
{

}
