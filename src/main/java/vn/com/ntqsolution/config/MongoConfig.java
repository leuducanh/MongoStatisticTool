package vn.com.ntqsolution.config;

import lombok.Getter;

public class MongoConfig {

    public static String host = "localhost";

    public static Integer port = new Integer(27017);

    public static Integer connectionsPerHost = new Integer(1500);

    public static String user = "Nexiv";

    public static String password = "100manUser";

    public static String authenticationDatabase = "admin";
}