package vn.com.ntqsolution;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.springframework.stereotype.Component;
import vn.com.ntqsolution.config.MongoConfig;
import vn.com.ntqsolution.constant.Constant;

import java.util.Arrays;

@Component
public class MongoDBManager {

    private static MongoDatabase apiLogDb;
    public static void createConnection(MongoConfig mongoConfig) {
        MongoCredential credential = MongoCredential.createCredential(mongoConfig.getUser(), mongoConfig.getAuthenticationDatabase(), mongoConfig.getPassword().toCharArray());
        MongoClientOptions option = new MongoClientOptions.Builder().connectionsPerHost(mongoConfig.getConnectionsPerHost()).build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(mongoConfig.getHost(), mongoConfig.getPort()), Arrays.asList(credential), option);

        apiLogDb = mongoClient.getDatabase(Constant.APILOG_DB);
    }

    public static MongoDatabase getApiLogDb() {
        return apiLogDb;
    }
}
