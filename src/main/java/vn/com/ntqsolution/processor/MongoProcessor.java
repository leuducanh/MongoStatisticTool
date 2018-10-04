package vn.com.ntqsolution.processor;

import com.mongodb.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.ntqsolution.MongoDBManager;

@Component
public class MongoProcessor {
    @Autowired
    MongoDBManager mongoDBManager;

    public MongoProcessor() {
    }


}
