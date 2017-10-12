package kr.or.fowi.daslim.daslim.presenter;

import kr.or.fowi.daslim.daslim.model.ScheduleInfo;

/**
 * Created by kimmihye on 2017. 10. 1..
 */

public interface MainPresenter {
    void checkLogined();

    interface View {

        void startLogin();

        void addFragment(ScheduleInfo info);

        void clearFragment();
    }
}
