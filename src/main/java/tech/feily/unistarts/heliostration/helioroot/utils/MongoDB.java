package tech.feily.unistarts.heliostration.helioroot.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public final class MongoDB {

    private static MongoDatabase mongoDatabase;
    
    private MongoDB() {
        
    }
    
    @SuppressWarnings("resource")
    public static synchronized MongoDatabase getInstance(String ip, String db) {
        if (mongoDatabase != null) {
            return mongoDatabase;
        }
        MongoClient mongoClient = new MongoClient(ip, 27017);
        mongoDatabase = mongoClient.getDatabase(db); 
        return mongoDatabase;
    }
    
}
