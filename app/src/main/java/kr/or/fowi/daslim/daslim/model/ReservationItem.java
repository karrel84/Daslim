package kr.or.fowi.daslim.daslim.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Rell on 2017. 10. 25..
 */

@IgnoreExtraProperties
public class ReservationItem {
    public int people;
    public String className; // 바다, 들, 강
    public String index; // 1회, 2회, 3회
    public String userNick;
    public String userId;
    public String userTelNum;

    public ReservationItem(int people, String className, String index) {
        this.people = people;
        this.className = className;
        this.index = index;
    }
}
