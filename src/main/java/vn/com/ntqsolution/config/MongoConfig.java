package vn.com.ntqsolution.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class MongoConfig {

    @Value("${application.database.mongodb.host}")
    private String host;

    @Value("${application.database.mongodb.port}")
    private Integer port;

    @Value("${application.database.mongodb.connectionsPerHost}")
    private Integer connectionsPerHost;

    @Value("${application.database.mongodb.user}")
    private String user;

    @Value("${application.database.mongodb.password}")
    private String password;

    @Value("${application.database.mongodb.authenticationDatabase}")
    private String authenticationDatabase;
}
