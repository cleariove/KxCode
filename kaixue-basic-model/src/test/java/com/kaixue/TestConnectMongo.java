package com.kaixue;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

public class TestConnectMongo
{
    @Test
    public void testConnection()
    {
        MongoClient client = new MongoClient("localhost",27017);
        MongoDatabase database = client.getDatabase("test");
        MongoCollection<Document> students = database.getCollection("student");
        Document firstStu = students.find().first();
        System.out.println(firstStu);
        System.out.println(firstStu.toJson());
    }
}
