package kr.or.fowi.daslim.daslim.presenter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.karrel.mylibrary.RLog;

import java.util.ArrayList;
import java.util.List;

import kr.or.fowi.daslim.daslim.etc.DataManger;
import kr.or.fowi.daslim.daslim.etc.LoginManager;
import kr.or.fowi.daslim.daslim.event.FirebaseEvent;
import kr.or.fowi.daslim.daslim.model.ScheduleInfo;
import kr.or.fowi.daslim.daslim.model.ScheduleInfoItem;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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
        DataManger.getInstance();
    }

    private void subscribeScheduleEvent() {
        // 혀냊 블루투스의 연결상태의 변경을 수신한다
        Observable<List<ScheduleInfo>> observable = FirebaseEvent.getInstance().getSheduleObservable();
        observable
                .onBackpressureDrop()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .distinct();

        observable.subscribe(scheduleInfos -> {
            view.clearFragment();
            RLog.d("scheduleInfos > " + scheduleInfos);
            view.setFragment(scheduleInfos);
        });
    }

    @Override
    public void checkLogined() {
        RLog.d("checkLogined");
        if (!loginManager.isLogined()) {
            view.startLogin();
        }
    }
}
