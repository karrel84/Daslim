package kr.or.fowi.daslim.daslim.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rell on 2017. 10. 12..
 */

@IgnoreExtraProperties
public class ScheduleInfoItem implements Parcelable {
    public String className; // 바다, 들, 강
    public String index; // 1회, 2회, 3회
    public long reserveCount; // 현재참여 인원
    public long maxReserve; // 최대 참여 인원
    public String time; // 진행 시간
    public Map<String, ReservationItem> reservationItemMap;


    public ScheduleInfoItem() {
        reservationItemMap = new HashMap<>();
    }

    public ScheduleInfoItem(long maxReserve, long reserveCount, String index, String className, String time) {
        reservationItemMap = new HashMap<>();
        this.className = className;
        this.index = index;
        this.reserveCount = reserveCount;
        this.maxReserve = maxReserve;
        this.time = time;
    }


    protected ScheduleInfoItem(Parcel in) {
        className = in.readString();
        index = in.readString();
        reserveCount = in.readLong();
        maxReserve = in.readLong();
        time = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(className);
        dest.writeString(index);
        dest.writeLong(reserveCount);
        dest.writeLong(maxReserve);
        dest.writeString(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ScheduleInfoItem> CREATOR = new Creator<ScheduleInfoItem>() {
        @Override
        public ScheduleInfoItem createFromParcel(Parcel in) {
            return new ScheduleInfoItem(in);
        }

        @Override
        public ScheduleInfoItem[] newArray(int size) {
            return new ScheduleInfoItem[size];
        }
    };

    @Override
    public String toString() {
        return "ScheduleInfoItem{" +
                "className='" + className + '\'' +
                ", index='" + index + '\'' +
                ", maxReserve=" + maxReserve +
                ", reserveCount=" + reserveCount +
                ", time='" + time + '\'' +
                '}';
    }

    public void addReservationInfo(String nick, ReservationItem reservationItem) {
        reservationItemMap.put(nick, reservationItem);
    }
}
