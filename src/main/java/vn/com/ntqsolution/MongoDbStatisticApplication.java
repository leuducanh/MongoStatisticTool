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

        mongoProcessor.statisticApiRequestToStf(new TimeRangeOption(args[0] + " " + args[1],args[2] + " " + args[3]), Constant.DES);
        System.out.println( "Start date: " + args[0] + " at: " + args[1] + " - End date: " + args[2] + " at: " + args[3] + " " + Constant.TIME_ZONE);
    }
    // DATE FORMAT: "dd-M-yyyy hh:mm:ss" Example: "22-01-2015 10:20:56";
}
