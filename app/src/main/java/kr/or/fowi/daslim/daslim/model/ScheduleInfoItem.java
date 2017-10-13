package kr.or.fowi.daslim.daslim.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Rell on 2017. 10. 12..
 */

@IgnoreExtraProperties
public class ScheduleInfoItem {
    public String index;
    public long curCount;
    public long maxCount;
    public String status;
    public String time;


    public ScheduleInfoItem() {
    }

    public ScheduleInfoItem(long curCount, long maxCount, String status, String time) {
        this.curCount = curCount;
        this.maxCount = maxCount;
        this.status = status;
        this.time = time;
    }
}
