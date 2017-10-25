package kr.or.fowi.daslim.daslim.view;

import com.karrel.mylibrary.RLog;

import kr.or.fowi.daslim.daslim.etc.DataManger;
import kr.or.fowi.daslim.daslim.model.ReservationItem;
import kr.or.fowi.daslim.daslim.model.ScheduleInfoItem;
import kr.or.fowi.daslim.daslim.presenter.ReservationPresenter;

/**
 * Created by Rell on 2017. 10. 24..
 */

public class ReservationPresenterImpl implements ReservationPresenter {
    private ReservationPresenter.View view;
    private ScheduleInfoItem item;
    private DataManger manager;


    public ReservationPresenterImpl(View view) {
        this.view = view;
        manager = DataManger.getInstance();
    }

    @Override
    public void setScheduleInfoItem(ScheduleInfoItem item) {
        this.item = item;
    }

    @Override
    public void reservation(int people) {
        ReservationItem item = new ReservationItem(people, this.item.className, this.item.index);
        item.people = people;
        manager.reservation(item);
        RLog.d(String.format("reservation(%s)", people));
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
        int count = (int) (item.maxCount - item.curCount);
        if (count > 10) count = 10;
        view.setMaxPeople(count);
    }
}
