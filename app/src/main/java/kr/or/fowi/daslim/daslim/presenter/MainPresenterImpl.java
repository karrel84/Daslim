package kr.or.fowi.daslim.daslim.presenter;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.karrel.mylibrary.RLog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kr.or.fowi.daslim.daslim.etc.LoginManager;

/**
 * Created by kimmihye on 2017. 10. 1..
 */

public class MainPresenterImpl implements MainPresenter {
    private MainPresenter.View view;
    private LoginManager loginManager;

    private DatabaseReference mReference;
    private FirebaseDatabase mDatabase;
    private Query mQuery;

    public MainPresenterImpl(MainPresenter.View view) {
        this.view = view;
        loginManager = LoginManager.getInstance();

        // 파이어베이스
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();

        subscribeScheduleEvent();
    }

    private void subscribeScheduleEvent() {
        RLog.e("subscribeScheduleEvent");
        mQuery = mReference.child("schedule");
        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                RLog.d("dataSnapshot key > " + dataSnapshot.getKey());
                RLog.d("dataSnapshot value > " + dataSnapshot.getValue());

                for (DataSnapshot snapshot : iterable) {
                    RLog.d("key > " + snapshot.getKey());
                    RLog.d("value > " + snapshot.getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void checkLogined() {
        // TODO: 2017. 10. 2. 로그인이 되어있는지 체크
        RLog.d("checkLogined");
        if (!loginManager.isLogined()) {
            view.startLogin();
        }
    }
}
