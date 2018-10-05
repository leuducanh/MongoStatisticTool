package vn.com.ntqsolution.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import vn.com.ntqsolution.MongoDBManager;
import vn.com.ntqsolution.constant.Constant;
import vn.com.ntqsolution.model.Result;
import vn.com.ntqsolution.model.TimeRangeOption;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StfDAO {

    private static MongoCollection<Document> coll;

    public StfDAO() {
        coll = MongoDBManager.getApiLogDb().getCollection(Constant.STF_COLLECTION);
    }

    public void insert(String api, long start, long dur){
        Document obj = new Document().append("api",api).append("start",start).append("duration",dur);
        coll.insertOne(obj);
    }

    public List<Result> filterWith(TimeRangeOption timeRangeOption,int sortOption){

//        String map = "function() {if ( this.start >= " + timeRangeOption.getStartDate() + " && this.start <= " + timeRangeOption.getEndDate() +") " + "emit(this.api, {count: 1}); "
//                + "else " + "emit(this.api, {count: 0});}";

        BasicDBObject objGt = new BasicDBObject();
        BasicDBObject objLt = new BasicDBObject();
        objGt.append("$gte", timeRangeOption.getStartDate());
        objLt.append("$lte", timeRangeOption.getEndDate());
        List<Document> apiDocWithinTime = coll.aggregate(Arrays.asList(
                new Document("$match", new Document("start", new Document().append("$gte",timeRangeOption.getStartDate())
                                                                            .append("$lte",timeRangeOption.getEndDate()))),
                new Document("$group", new Document().append("_id","$api")
                                                        .append("count",new Document("$sum",1))),
                new Document("$sort", new Document().append("count",sortOption))
        )).into(new LinkedList<Document>());

        List<Result> results = new LinkedList<>();
        for(Document doc : apiDocWithinTime){
            Result result = new Result();
            result.apiName = (String) doc.get("_id");
            result.requestCount = (Integer) doc.get("count");
            results.add(result);
        }
        return results;
    }
}
