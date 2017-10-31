package kr.or.fowi.daslim.daslim.presenter;

import java.util.List;

import kr.or.fowi.daslim.daslim.model.ScheduleInfo;

/**
 * Created by kimmihye on 2017. 10. 1..
 */

public interface MainPresenter {
    void checkLogined();

    interface View {

        void startLogin();

        void clearFragment();

        void setFragment(List<ScheduleInfo> scheduleInfos);

        void showProgress();

        void hideProgress();
    }
}
