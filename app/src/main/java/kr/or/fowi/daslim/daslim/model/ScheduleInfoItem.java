package kr.or.fowi.daslim.daslim.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Rell on 2017. 10. 12..
 */

@IgnoreExtraProperties
public class ScheduleInfoItem implements Parcelable {
    public String className; // 바다, 들, 강
    public String index; // 1회, 2회, 3회
    public long curCount; // 현재참여 인원
    public long maxCount; // 최대 참여 인원
    public String status;
    public String time; // 진행 시간


    public ScheduleInfoItem() {
    }

    public ScheduleInfoItem(long curCount, long maxCount, String status, String time) {
        this.curCount = curCount;
        this.maxCount = maxCount;
        this.status = status;
        this.time = time;
    }

    protected ScheduleInfoItem(Parcel in) {
        index = in.readString();
        curCount = in.readLong();
        maxCount = in.readLong();
        status = in.readString();
        time = in.readString();
        className = in.readString();
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(index);
        parcel.writeLong(curCount);
        parcel.writeLong(maxCount);
        parcel.writeString(status);
        parcel.writeString(time);
        parcel.writeString(className);
    }

    @Override
    public String toString() {
        return "ScheduleInfoItem{" +
                "className='" + className + '\'' +
                ", curCount=" + curCount +
                ", index='" + index + '\'' +
                ", maxCount=" + maxCount +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                '}';
    }


    public void setClass(String className) {

    }


}
