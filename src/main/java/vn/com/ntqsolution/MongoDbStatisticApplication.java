package vn.com.ntqsolution;

import vn.com.ntqsolution.constant.Constant;
import vn.com.ntqsolution.dao.StfDAO;
import vn.com.ntqsolution.model.TimeRangeOption;
import vn.com.ntqsolution.processor.MongoProcessor;
import vn.com.ntqsolution.util.FileIOHelper;

public class MongoDbStatisticApplication {

    public static void main(String[] args) {
        FileIOHelper.FOLDER_NAME = "statistic_api";
        Constant.STF_COLLECTION = "main";

        FileIOHelper.initFolder();
        MongoDBManager.createConnection();

        MongoProcessor mongoProcessor = new MongoProcessor();
        mongoProcessor.statisticApiRequestToStf(new TimeRangeOption("22-01-2016 10:20:56","10-10-2018 10:20:56"), Constant.DES);

    }
    // DATE FORMAT: "dd-M-yyyy hh:mm:ss" Example: "22-01-2015 10:20:56";
}
