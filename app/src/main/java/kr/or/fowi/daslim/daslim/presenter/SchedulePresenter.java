package kr.or.fowi.daslim.daslim.presenter;

import kr.or.fowi.daslim.daslim.model.ReservationItem;
import kr.or.fowi.daslim.daslim.model.ScheduleInfo;
import kr.or.fowi.daslim.daslim.model.ScheduleInfoItem;

/**
 * Created by Rell on 2017. 10. 12..
 */

public interface SchedulePresenter {
    void setData(ScheduleInfo info);

    void onViewCreated();

    String getTitle();

    interface View {

        void addInfoItem(ScheduleInfoItem item);

        void addReserveItem(ReservationItem item);
    }
}
