package kr.or.fowi.daslim.daslim.presenter;

import kr.or.fowi.daslim.daslim.model.ScheduleInfo;

/**
 * Created by Rell on 2017. 10. 12..
 */

public class SchedulePresenterImpl implements SchedulePresenter {
    private SchedulePresenter.View view;

    public SchedulePresenterImpl(View view) {
        this.view = view;
    }

    @Override
    public void setData(ScheduleInfo info) {
    }
}
