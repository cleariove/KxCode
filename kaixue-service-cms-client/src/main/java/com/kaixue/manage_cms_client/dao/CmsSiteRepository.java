package com.kaixue.manage_cms_client.dao;

import com.kaixue.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsSiteRepository extends MongoRepository<CmsSite, String>
{
}
