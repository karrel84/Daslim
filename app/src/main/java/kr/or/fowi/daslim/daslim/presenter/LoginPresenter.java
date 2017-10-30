package kr.or.fowi.daslim.daslim.presenter;

/**
 * Created by Rell on 2017. 10. 2..
 */

public interface LoginPresenter {

    void join();

    void login(String id, String pw);

    interface View {

        void finish();

        void startJoinActivity();
    }
}
