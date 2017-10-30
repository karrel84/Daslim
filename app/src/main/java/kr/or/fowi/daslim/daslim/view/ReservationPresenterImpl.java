package kr.or.fowi.daslim.daslim.view;

import com.karrel.mylibrary.RLog;

import java.util.List;

import kr.or.fowi.daslim.daslim.etc.DataManager;
import kr.or.fowi.daslim.daslim.event.FirebaseEvent;
import kr.or.fowi.daslim.daslim.model.ReservationItem;
import kr.or.fowi.daslim.daslim.model.ScheduleInfo;
import kr.or.fowi.daslim.daslim.model.ScheduleInfoItem;
import kr.or.fowi.daslim.daslim.presenter.ReservationPresenter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Rell on 2017. 10. 24..
 */

public class ReservationPresenterImpl implements ReservationPresenter {
    private ReservationPresenter.View view;
    private ScheduleInfoItem item;
    private DataManager manager;
    private ReservationItem mReserveItem;

    public ReservationPresenterImpl(View view) {
        this.view = view;
        manager = DataManager.getInstance();

    }

    @Override
    public void setScheduleInfoItem(ScheduleInfoItem item) {
        this.item = item;
    }

    @Override
    public void reservation(int people) {

        mReserveItem = new ReservationItem(people, this.item.className, this.item.index, this.item.time);
        mReserveItem.people = people;
        manager.reservation(mReserveItem);
        RLog.d(String.format("reservation(%s)", people));
        view.finish();
    }

    @Override
    public void setupData() {
        // setup class
        view.setClass(item.className);

        // setup 회차
        view.setIndex(item.index);

        // setup 시간
        view.setTime(item.time);

        // setup 인원
        int count = (int) (item.maxReserve - item.reserveCount);
        if (count > 10) count = 10;
        view.setMaxPeople(count);
    }
}
