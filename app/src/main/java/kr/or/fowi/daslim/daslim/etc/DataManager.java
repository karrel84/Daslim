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
import kr.or.fowi.daslim.daslim.model.ReservationItem;
import kr.or.fowi.daslim.daslim.model.ScheduleInfo;
import kr.or.fowi.daslim.daslim.model.ScheduleInfoItem;
import kr.or.fowi.daslim.daslim.model.UserInfo;

/**
 * Created by Rell on 2017. 10. 16..
 */

public class DataManager {
    private DatabaseReference mReference;
    private FirebaseDatabase mDatabase;
    private Query mQuery;

    private static DataManager instance = new DataManager();

    private List<ScheduleInfo> scheduleInfoList;
    private List<UserInfo> userInfos;

    public static DataManager getInstance() {
        return instance;
    }

    public DataManager() {
        // 파이어베이스
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();

        // 스케쥴 수신
        subscribeScheduleEvent();
        // 유저정보 수신
        subscribeUserEvent();
    }

    private void subscribeUserEvent() {
        userInfos = new ArrayList<>();
        mQuery = mReference.child("user");
        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                RLog.e("subscribeUserEvent");

                // clear list
                if (!userInfos.isEmpty()) userInfos.clear();

                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                for (DataSnapshot s : iterable) {
                    String name = s.child("name").getValue(String.class);
                    String nick = s.child("nick").getValue(String.class);
                    String tel = s.child("tel").getValue(String.class);

                    UserInfo infoItem = new UserInfo(name, nick, tel);
                    userInfos.add(infoItem);
                    RLog.d("user value : " + infoItem.toString());
                }

                FirebaseEvent.getInstance().sendUserEvent(userInfos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void subscribeScheduleEvent() {
        scheduleInfoList = new ArrayList<>();
        RLog.e("subscribeScheduleEvent");
        mQuery = mReference.child("schedule");
        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // clear list
                if (!scheduleInfoList.isEmpty()) scheduleInfoList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // 바다, 강, 들 이런 클래스 이름
                    List<ScheduleInfoItem> list = new ArrayList<>();
                    for (DataSnapshot indexItem : snapshot.getChildren()) {
                        // 1회, 2회, 3회

                        ScheduleInfoItem infoItem = new ScheduleInfoItem();
                        infoItem.className = (String) indexItem.child("className").getValue();
                        infoItem.index = (String) indexItem.child("index").getValue();
                        infoItem.maxReserve = (long) indexItem.child("maxReserve").getValue();
                        infoItem.reserveCount = (long) indexItem.child("reserveCount").getValue();
                        infoItem.time = (String) indexItem.child("time").getValue();

                        list.add(infoItem);
                        RLog.d(infoItem.toString());

                        for (DataSnapshot reservationUsers : indexItem.getChildren()) {
                            String nick = reservationUsers.getKey();
                            ReservationItem reservationItem = reservationUsers.getValue(ReservationItem.class);
                            infoItem.addReservationInfo(nick, reservationItem);
                        }
                    }
                    ScheduleInfo info = new ScheduleInfo(snapshot.getKey(), list);

                    scheduleInfoList.add(info);
                }

                FirebaseEvent.getInstance().sendScheduleEvent(scheduleInfoList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void join(String name, String nick, String telNumber) {
        UserInfo info = new UserInfo(name, nick, telNumber);
        mReference.child("user").child(name).setValue(info);
    }

    public void reservation(ReservationItem item) {
        item.userId = getUserId();
        item.userNick = getUserName();
        item.userTelNum = getUserTel();

        mReference.child("schedule").child(item.className).child(item.index).child("reservation").child(item.userId).setValue(item);
    }

    public String getUserName() {
        return PP.name.get();
    }

    public String getUserId() {
        return PP.nick.get();
    }

    public String getUserTel() {
        return PP.tel.get();
    }
}
