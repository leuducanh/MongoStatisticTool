package vn.com.ntqsolution.processor;

import com.mongodb.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.ntqsolution.MongoDBManager;
import vn.com.ntqsolution.dao.StfDAO;
import vn.com.ntqsolution.model.Result;
import vn.com.ntqsolution.model.TimeRangeOption;
import vn.com.ntqsolution.util.FileIOHelper;

import java.util.List;

@Component
public class MongoProcessor {

    @Autowired
    StfDAO stfDAO;

    public void statisticApiRequestToStf(TimeRangeOption timeRangeOption){
        List<Result> results = stfDAO.filterWith(timeRangeOption);

        FileIOHelper.createFile(timeRangeOption.getTimeRange());
        FileIOHelper.write(results,timeRangeOption.getTimeRange());
    }
}
