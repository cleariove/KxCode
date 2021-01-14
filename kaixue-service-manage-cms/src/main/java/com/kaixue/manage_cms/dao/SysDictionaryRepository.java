package com.kaixue.manage_cms.dao;

import com.kaixue.domain.system.SysDictionary;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SysDictionaryRepository extends MongoRepository<SysDictionary,String>
{
    SysDictionary findByDType(String type);
}
