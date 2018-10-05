package vn.com.ntqsolution.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

public class MongoConfig {

    public static String host = "localhost";

    public static Integer port = new Integer(27017);

    public static Integer connectionsPerHost = new Integer(1500);

    public static String user = "Nexiv";

    public static String password = "100manUser";

    public static String authenticationDatabase = "admin";
}
