package com.kaixue.manage_cms;


import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//加载ApplicationContext，启动spring容器。
@SpringBootTest
//将Spring和Junit连接起来
@RunWith(SpringRunner.class)
public class TestGridFs
{
    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    @Test
    public void testGridFs() throws IOException
    {
//        文件存储成功得到一个文件id
//        此文件id是fs.files集合中的主键。
//        可以通过文件id查询fs.chunks表中的记录，得到文件的内容。
        //待存储的文件
        File file = new File("d:/index_banner.ftl");
        //定义文件输出流
        FileInputStream fis = new FileInputStream(file);
        System.out.println(file.exists());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
        //往GridFs中存储文件
        ObjectId id = gridFsTemplate.store(fis, "index_banner.ftl","");
        //获得文件Id
        String fileId = id.toString();
        System.out.println(fileId);
        fis.close();
    }
    @Test
    public void testGet() throws IOException
    {
        String fid = "5fefe4f03aa3fc0bc4d115db";
        fid = "5fefe8393aa3fc3970809017";
        //查询文件Id
        GridFSFile fsFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fid)));
        //打开下载流
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(fsFile.getObjectId());
        //创建GridFsResource获取流对象
        GridFsResource gridFsResource = new GridFsResource(fsFile,downloadStream);
        //获取流中数据
        String s = IOUtils.toString(gridFsResource.getInputStream(), StandardCharsets.UTF_8);
        System.out.println(s);
    }
}
