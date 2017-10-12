package kr.or.fowi.daslim.daslim.model;

import java.util.List;

/**
 * Created by Rell on 2017. 10. 11..
 */

public class ScheduleInfo {
    public String title;
    public List<ScheduleInfoItem> scheduleInfoItems;

    public ScheduleInfo(String title, List<ScheduleInfoItem> scheduleInfoItems) {
        this.title = title;
        this.scheduleInfoItems = scheduleInfoItems;
    }

    @Override
    public String toString() {
        return "ScheduleInfo{" +
                "scheduleInfoItems=" + scheduleInfoItems +
                ", title='" + title + '\'' +
                '}';
    }
}
