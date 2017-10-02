package kr.or.fowi.daslim.daslim.presenter;

import com.karrel.mylibrary.RLog;

import kr.or.fowi.daslim.daslim.etc.LoginManager;

/**
 * Created by kimmihye on 2017. 10. 1..
 */

public class MainPresenterImpl implements MainPresenter {
    private MainPresenter.View view;
    private LoginManager loginManager;

    public MainPresenterImpl(MainPresenter.View view) {
        this.view = view;
        loginManager = LoginManager.getInstance();

    }

    @Override
    public void checkLogined() {
        // TODO: 2017. 10. 2. 로그인이 되어있는지 체크
        RLog.d("checkLogined");
        if (!loginManager.isLogined()) {
            view.startLogin();
        }
    }
}
