package kr.or.fowi.daslim.daslim.presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.karrel.mylibrary.RLog;

import kr.or.fowi.daslim.daslim.etc.DataManager;
import kr.or.fowi.daslim.daslim.etc.LoginManager;
import kr.or.fowi.daslim.daslim.event.FirebaseEvent;

/**
 * Created by kimmihye on 2017. 10. 1..
 */

public class ReservePresenterImpl implements ReservePresenter {
    private ReservePresenter.View view;
    private LoginManager loginManager;

    private DatabaseReference mReference;
    private FirebaseDatabase mDatabase;
    private Query mQuery;

    public ReservePresenterImpl(ReservePresenter.View view) {
        this.view = view;
        loginManager = LoginManager.getInstance();

        // 파이어베이스
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();

        subscribeScheduleEvent();
        DataManager.getInstance();

        view.showProgress();
    }

    private void subscribeScheduleEvent() {
        // 혀냊 블루투스의 연결상태의 변경을 수신한다
        FirebaseEvent.getInstance().getSheduleObservable().subscribe(scheduleInfos -> {
//            view.clearFragment();
            RLog.d("scheduleInfos > " + scheduleInfos);
            view.setFragment(scheduleInfos);
            view.hideProgress();
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
