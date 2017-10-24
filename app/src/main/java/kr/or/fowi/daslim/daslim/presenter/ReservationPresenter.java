package kr.or.fowi.daslim.daslim.presenter;

import kr.or.fowi.daslim.daslim.model.ScheduleInfoItem;

/**
 * Created by Rell on 2017. 10. 24..
 */

public interface ReservationPresenter {
    void setupData();

    void setScheduleInfoItem(ScheduleInfoItem item);

    void reservation(int people);

    interface View {

        void setClass(String className);

        void setIndex(String index);

        void setTime(String time);

        void setMaxPeople(int count);
    }
}
