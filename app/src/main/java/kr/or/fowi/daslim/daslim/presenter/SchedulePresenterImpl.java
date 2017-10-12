package kr.or.fowi.daslim.daslim.presenter;

import kr.or.fowi.daslim.daslim.model.ScheduleInfo;
import kr.or.fowi.daslim.daslim.model.ScheduleInfoItem;

/**
 * Created by Rell on 2017. 10. 12..
 */

public class SchedulePresenterImpl implements SchedulePresenter {
    private SchedulePresenter.View view;
    private ScheduleInfo scheduleInfo;

    public SchedulePresenterImpl(View view) {
        this.view = view;
    }

    @Override
    public void setData(ScheduleInfo info) {
        scheduleInfo = info;
    }

    @Override
    public void onViewCreated() {
        // add item for adapter
        for (ScheduleInfoItem item : scheduleInfo.scheduleInfoItems) {
            view.addInfoItem(item);
        }
    }

    @Override
    public String getTitle() {
        return scheduleInfo.title;
    }
}
