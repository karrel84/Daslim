package kr.or.fowi.daslim.daslim.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Rell on 2017. 10. 12..
 */

@IgnoreExtraProperties
public class ScheduleInfoItem {
    // 예약수
    public long size;
    // 시간
    public String time;

    public ScheduleInfoItem() {
    }

    public ScheduleInfoItem(long size, String time) {
        this.size = size;
        this.time = time;
    }


    @Override
    public String toString() {
        return "ScheduleInfoItem{" +
                "size=" + size +
                ", time='" + time + '\'' +
                '}';
    }
}
