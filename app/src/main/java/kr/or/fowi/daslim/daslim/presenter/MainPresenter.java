package kr.or.fowi.daslim.daslim.presenter;

/**
 * Created by kimmihye on 2017. 10. 1..
 */

public interface MainPresenter {
    void checkLogined();

    interface View {

        void startLogin();
    }
}