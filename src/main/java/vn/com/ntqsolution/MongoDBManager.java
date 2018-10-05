package vn.com.ntqsolution;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import vn.com.ntqsolution.config.MongoConfig;
import vn.com.ntqsolution.constant.Constant;

import java.util.Arrays;

public class MongoDBManager {

    private static MongoDatabase apiLogDb;

    public MongoDBManager() {
        createConnection();
    }

    public static void createConnection() {
        MongoCredential credential = MongoCredential.createCredential(MongoConfig.user, MongoConfig.authenticationDatabase, MongoConfig.password.toCharArray());
        MongoClientOptions option = new MongoClientOptions.Builder().connectionsPerHost(MongoConfig.connectionsPerHost).build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(MongoConfig.host, MongoConfig.port), Arrays.asList(credential), option);

        apiLogDb = mongoClient.getDatabase(Constant.APILOG_DB);
    }

    public static MongoDatabase getApiLogDb() {
        return apiLogDb;
    }
}
