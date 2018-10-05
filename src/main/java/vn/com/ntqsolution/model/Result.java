package vn.com.ntqsolution.model;

public class Result {

    public String apiName;
    public int requestCount;

    @Override
    public String toString() {
        return apiName  + " " + requestCount;
    }
}
