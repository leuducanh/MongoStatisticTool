package vn.com.ntqsolution;

import vn.com.ntqsolution.config.MongoConfig;
import vn.com.ntqsolution.constant.Constant;
import vn.com.ntqsolution.dao.StfDAO;
import vn.com.ntqsolution.model.TimeRangeOption;
import vn.com.ntqsolution.processor.MongoProcessor;
import vn.com.ntqsolution.util.FileIOHelper;

import java.util.ArrayList;

public class MongoDbStatisticApplication {

    public static void main(String[] args) {
        FileIOHelper.FOLDER_NAME = "statistic_api";
        Constant.STF_COLLECTION = "stf";
        Constant.TIME_ZONE = "GMT+0";
        MongoConfig.host = "localhost";
        MongoConfig.port = new Integer(27017);
        MongoConfig.connectionsPerHost = new Integer(1500);
        MongoConfig.user = "Nexiv";
        MongoConfig.password = "100manUser";
        MongoConfig.authenticationDatabase = "admin";

        FileIOHelper.initFolder();
        MongoDBManager.createConnection();

        MongoProcessor mongoProcessor = new MongoProcessor();

        mongoProcessor.statisticApiRequestToStf(new TimeRangeOption(Long.parseLong(args[0]),Long.parseLong(args[1])), Constant.DES);
        System.out.println( "Start milisecond: " + args[0] + " - End milisecond: " + args[1]);
    }
    // DATE FORMAT: "dd-M-yyyy hh:mm:ss" Example: "22-01-2015 10:20:56";
}
