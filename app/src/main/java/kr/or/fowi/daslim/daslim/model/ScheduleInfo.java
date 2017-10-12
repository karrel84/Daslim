package kr.or.fowi.daslim.daslim.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Rell on 2017. 10. 11..
 */

@IgnoreExtraProperties
public class ScheduleInfo implements Parcelable {
    protected ScheduleInfo(Parcel in) {
    }

    public static final Creator<ScheduleInfo> CREATOR = new Creator<ScheduleInfo>() {
        @Override
        public ScheduleInfo createFromParcel(Parcel in) {
            return new ScheduleInfo(in);
        }

        @Override
        public ScheduleInfo[] newArray(int size) {
            return new ScheduleInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
