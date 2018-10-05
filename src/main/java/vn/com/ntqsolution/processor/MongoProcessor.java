package vn.com.ntqsolution.processor;

import vn.com.ntqsolution.dao.StfDAO;
import vn.com.ntqsolution.model.Result;
import vn.com.ntqsolution.model.TimeRangeOption;
import vn.com.ntqsolution.util.FileIOHelper;

import java.util.List;

public class MongoProcessor {

    StfDAO stfDAO;

    public MongoProcessor() {
        stfDAO = new StfDAO();
    }

    public void statisticApiRequestToStf(TimeRangeOption timeRangeOption, int sortOption){
        List<Result> results = stfDAO.filterWith(timeRangeOption,sortOption);

        FileIOHelper.createFile(timeRangeOption.getTimeRange());
        FileIOHelper.write(results,timeRangeOption.getTimeRange());
    }
}
