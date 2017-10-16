package kr.or.fowi.daslim.daslim.etc;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.karrel.mylibrary.RLog;

import java.util.ArrayList;
import java.util.List;

import kr.or.fowi.daslim.daslim.event.FirebaseEvent;
import kr.or.fowi.daslim.daslim.model.ScheduleInfo;
import kr.or.fowi.daslim.daslim.model.ScheduleInfoItem;

/**
 * Created by Rell on 2017. 10. 16..
 */

public class DataManger {
    private DatabaseReference mReference;
    private FirebaseDatabase mDatabase;
    private Query mQuery;

    private static DataManger instance = new DataManger();

    private List<ScheduleInfo> scheduleInfoList;

    public static DataManger getInstance() {
        return instance;
    }

    public DataManger() {
        // 파이어베이스
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();

        scheduleInfoList = new ArrayList<>();
        subscribeScheduleEvent();
    }


    private void subscribeScheduleEvent() {
        RLog.e("subscribeScheduleEvent");
        mQuery = mReference.child("schedule");
        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // clear list
                if (!scheduleInfoList.isEmpty()) scheduleInfoList.clear();

                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                for (DataSnapshot snapshot : iterable) {
                    List<ScheduleInfoItem> list = new ArrayList<>();
                    for (int i = 0; i < snapshot.getChildrenCount(); i++) {
                        final String key = i + 1 + "";
                        ScheduleInfoItem infoItem = snapshot.child(key).getValue(ScheduleInfoItem.class);
                        infoItem.index = key;
                        list.add(infoItem);
                        RLog.e("infoItem.toString() > " + infoItem.toString());
                    }
                    ScheduleInfo info = new ScheduleInfo(snapshot.getKey(), list);
                    RLog.d("info.toString() > " + info.toString());

                    scheduleInfoList.add(info);
                }

                FirebaseEvent.getInstance().sendEvent(scheduleInfoList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
