package tech.feily.unistarts.heliostration.helioroot.utils;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class BlockChain {

    public static void insert(String collection, String blockjson) {
        MongoDatabase mgdb = MongoDB.getInstance(PreCmd.getParam().get("dbHost"), PreCmd.getParam().get("dbname"));
        MongoCollection<Document> clct = mgdb.getCollection(collection);
        Document doc = Document.parse(blockjson);
        clct.insertOne(doc);
    }
    
}
