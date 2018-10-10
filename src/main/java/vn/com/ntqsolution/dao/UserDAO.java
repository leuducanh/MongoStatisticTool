package vn.com.ntqsolution.dao;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import vn.com.ntqsolution.MongoDBManager;
import vn.com.ntqsolution.constant.Constant;
import vn.com.ntqsolution.model.Result;
import vn.com.ntqsolution.model.TimeRangeOption;

import java.util.*;
import java.util.function.Consumer;

public class UserDAO {
    private static MongoCollection<Document> coll;
    private static MongoCollection<Document> collLogPoint;

    public UserDAO() {
        coll = MongoDBManager.getUserDb().getCollection("user");
        collLogPoint = MongoDBManager.getLogpointDb().getCollection("log_point");
    }

    public void insert(String name, int point, Date date){
        Document basicDBObject = new Document().append("name",name)
                                                        .append("point",point)
                                                        .append("date",date);
        coll.insertOne(basicDBObject);
    }

    public void get(String name){
        BasicDBObject dbObject = new BasicDBObject().append("name",name);

        Document dbObject1 = coll.find(dbObject).first();

        System.out.println("name: " + dbObject1.get("name"));
        System.out.println("point: " + dbObject1.get("point"));
        System.out.println("date: " + dbObject1.get("date"));

    }

    public void getList(String name){
        BasicDBObject dbObject = new BasicDBObject().append("name",name);

        Document dbObject1 = coll.find(dbObject).first();

        System.out.println("name: " + dbObject1.get("name"));

        ArrayList<Integer> lst = (ArrayList<Integer>) dbObject1.get("lst");
        System.out.println("lst" + lst.size());
    }

    public void getListObj(String name){
        BasicDBObject dbObject = new BasicDBObject().append("name",name);

        Document dbObject1 = coll.find(dbObject).first();

        System.out.println("name: " + dbObject1.get("name"));

        ArrayList<Document> lst = (ArrayList) dbObject1.get("lst");
        System.out.println("lst" + lst.size() + " " + lst.get(0).get("name"));
    }

    public void statistic(int month, int year){
        BasicDBObject dbObject = new BasicDBObject().append("gender",1);
        MongoCursor<Document> cursor = coll.find(dbObject).projection(new BasicDBObject().append("_id",true)).iterator();
        List<String> usreIds = new LinkedList<>();
        while(cursor.hasNext()){
            String s  = cursor.next().get("_id").toString();
            usreIds.add(s);
            System.out.println(s);
        }

        BasicDBObject match1 = new BasicDBObject().append("$match",new BasicDBObject().append("user_id",new BasicDBObject("$in",usreIds)));
        BasicDBObject project1 = new BasicDBObject().append("$project",new BasicDBObject().append("time",new BasicDBObject("$dateFromString",new BasicDBObject().append("dateString","$time")
                                                                                                                                                                            .append("format","%Y%m%d%H%M%S")))
                                                                                                .append("type",true)
                                                                                                .append("point", true));
        BasicDBObject project2 = new BasicDBObject().append("$project",new BasicDBObject().append("day",new BasicDBObject().append("$dayOfMonth","$time"))
                                                                                                .append("date",new BasicDBObject().append("$dayOfWeek","$time"))
                                                                                                .append("month",new BasicDBObject().append("$month","$time"))
                                                                                                .append("year",new BasicDBObject().append("$year","$time"))
                                                                                                .append("type",true)
                                                                                                .append("point",true));
        BasicDBObject match2 = new BasicDBObject().append("$match",new BasicDBObject().append("month",month)
                                                                                            .append("year",year));
        BasicDBObject group = new BasicDBObject().append("$group",new BasicDBObject().append("_id", new BasicDBObject().append("type","$type")
                                                                                                                                .append("date","$date")
                                                                                                                                .append("day","$day"))
                                                                                            .append("totalpoint",new BasicDBObject().append("$sum","$point")));
        BasicDBObject sort = new BasicDBObject().append("$sort",new BasicDBObject().append("_id.day",1));

        List<Document> lst1 = collLogPoint.aggregate(Arrays.asList(
                match1,
                project1,
                project2,
                match2,
                group,
                sort
        )).into(new LinkedList<Document>());

        lst1.forEach(document -> {
            System.out.println( "date: " + ((Document)document.get("_id")).get("date") + " day: " + ((Document)document.get("_id")).get("day") + " type: " + ((Document)document.get("_id")).get("type") + " totalpoint: " + document.get("totalpoint"));
        });


    }

    public void doSomeThing() {

//        String map = "function() {if ( this.start >= " + timeRangeOption.getStartDate() + " && this.start <= " + timeRangeOption.getEndDate() +") " + "emit(this.api, {count: 1}); "
//                + "else " + "emit(this.api, {count: 0});}";

        BasicDBObject bquery = new BasicDBObject().append("point", new BasicDBObject()
                .append("$gt", 0)
                .append("$lt", 1000))
                .append("flag", new BasicDBObject().append("$ne", 0));
        BasicDBObject bincludes = new BasicDBObject().append("point", true)
                .append("flag", true);
        BasicDBObject sort = new BasicDBObject().append("point",1);
        List<Document> lst = coll.find(bquery).projection(bincludes).sort(sort).into(new ArrayList<Document>());

        Document dbObject1 = coll.find(bquery).projection(bincludes).sort(sort).first();

        System.out.println("document find one: " + dbObject1.get("point"));

        List<Document> lst1 = coll.find().skip(3).limit(3).into(new ArrayList<Document>());
        System.out.println(lst.size() + " size ");

        lst1.forEach(x -> {
            System.out.println(" " + x.get("_id"));
        });
    }
}
