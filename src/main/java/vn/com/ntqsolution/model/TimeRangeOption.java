package vn.com.ntqsolution.model;

import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class TimeRangeOption {

    private Long startDate;
    private Long endDate;
    private String timeRange;

    public TimeRangeOption() {
    }

    public TimeRangeOption(String startDate, String endDate) {
        this.timeRange = "";
        this.startDate = null;
        this.endDate = null;
        if (startDate != null && !startDate.isEmpty()) {
            this.startDate = parse(startDate);
            timeRange += startDate;
        }
        if (endDate != null && !endDate.isEmpty()) {
            this.endDate = parse(endDate);
            timeRange += " " + endDate;
        }
        timeRange = timeRange.replaceAll("\\:"," ");
    }

    public static long parse(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
}
