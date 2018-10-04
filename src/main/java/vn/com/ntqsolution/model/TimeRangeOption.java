package vn.com.ntqsolution.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class TimeRangeOption {

    private long startDate;
    private long endDate;

    public TimeRangeOption() {
    }

    public TimeRangeOption(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

        startDate = null;
        endDate = null;
        try {
            if(startDate != null && !startDate.isEmpty()){
                Date start = sdf.parse(startDate);
                this.startDate = start.getTime();
            }
            if(endDate != null && !endDate.isEmpty()){
                Date end = sdf.parse(endDate);
                this.endDate = end.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
