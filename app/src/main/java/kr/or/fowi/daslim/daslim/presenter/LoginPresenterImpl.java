package kr.or.fowi.daslim.daslim.presenter;

import kr.or.fowi.daslim.daslim.etc.LoginManager;

/**
 * Created by Rell on 2017. 10. 2..
 */

public class LoginPresenterImpl implements LoginPresenter {
    private LoginPresenter.View view;
    private LoginManager loginManager;

    public LoginPresenterImpl(View view) {
        this.view = view;
        loginManager = LoginManager.getInstance();
    }

    @Override
    public void join() {
        view.startJoinActivity();
    }

    @Override
    public void login(String id, String pw) {
        // TODO: 2017. 10. 2. 유효성체크

        // 2017. 10. 2. 로그인
        if (loginManager.login(id, pw)) {
            view.finish();
        } else {
            // TODO: 2017. 10. 2. 로그인실패
        }

    }
}
