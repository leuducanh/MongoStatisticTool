package vn.com.ntqsolution.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Component;
import vn.com.ntqsolution.MongoDBManager;
import vn.com.ntqsolution.constant.Constant;
import vn.com.ntqsolution.model.MapreduceResult;
import vn.com.ntqsolution.model.TimeRangeOption;

import java.util.Arrays;
import java.util.List;

@Component
public class StfDAO {

    private static MongoCollection<Document> coll;

    static {
        try {
            coll = MongoDBManager.getApiLogDb().getCollection(Constant.STF_COLLECTION);
        } catch (Exception ex) {

        }
    }

    public void mapreduceWith(TimeRangeOption timeRangeOption){

//        String map = "function() {if ( this.start >= " + timeRangeOption.getStartDate() + " && this.start <= " + timeRangeOption.getEndDate() +") " + "emit(this.api, {count: 1}); "
//                + "else " + "emit(this.api, {count: 0});}";

        BasicDBObject objGt = new BasicDBObject();
        BasicDBObject objLt = new BasicDBObject();
        objGt.append("$gte", timeRangeOption.getStartDate());
        objLt.append("$lte", timeRangeOption.getEndDate());
        AggregateIterable<Document> apiDocWithinTime = coll.aggregate(Arrays.asList(
                new Document("$match", new Document("start", new Document().append("$gte",timeRangeOption.getStartDate())
                                                                            .append("$lte",timeRangeOption.getEndDate()))),
                new Document("$group", new Document().append("_id","$api")
                                                        .append("count",new Document("$sum",1)))
        ));

        for(Document doc : apiDocWithinTime){
            System.out.println(doc);
        }
    }
}
