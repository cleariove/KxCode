package com.kaixue.manage_cms.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {
    //这个表示application.yml文件中的值
    @Value("${spring.data.mongodb.database}")
    String db;

    // 这个bean返回了MongoDB中的默认存储桶，默认是文件保存在fs.chunks中
    // 这个是默认的存储桶，可以通过设置改成我们自定义的桶
    // https://blog.csdn.net/crownp/article/details/103887116
    @Bean
    public GridFSBucket getGridFSBucket(MongoClient mongoClient){
        MongoDatabase database = mongoClient.getDatabase(db);
        return GridFSBuckets.create(database);
    }
}