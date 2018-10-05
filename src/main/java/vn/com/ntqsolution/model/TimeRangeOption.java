package vn.com.ntqsolution.model;

import lombok.Getter;
import vn.com.ntqsolution.constant.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Getter
public class TimeRangeOption {

    private Long startDate;
    private Long endDate;
    private String timeRange;

    public TimeRangeOption() {
    }

    public TimeRangeOption(Long startDate, Long endDate) {
        this.timeRange = "";
        this.startDate = null;
        this.endDate = null;
        if (startDate != null) {
            this.startDate = startDate;
            timeRange += startDate;
        }
        if (endDate != null) {
            this.endDate = endDate;
            timeRange += " " + endDate;
        }
    }
}
