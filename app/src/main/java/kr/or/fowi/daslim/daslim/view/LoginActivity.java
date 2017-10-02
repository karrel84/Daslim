package kr.or.fowi.daslim.daslim.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import kr.or.fowi.daslim.daslim.R;
import kr.or.fowi.daslim.daslim.base.BaseActivity;
import kr.or.fowi.daslim.daslim.databinding.ActivityLoginBinding;
import kr.or.fowi.daslim.daslim.presenter.LoginPresenter;
import kr.or.fowi.daslim.daslim.presenter.LoginPresenterImpl;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginPresenter.View {

    private ActivityLoginBinding binding;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        presenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void onLoadOnce() {
        super.onLoadOnce();
        // login
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String id = binding.loginIdText.getText().toString();
                final String pw = binding.loginPwText.getText().toString();
                presenter.login(id, pw);
            }
        });
        // clicked join
        binding.join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.join();
            }
        });
    }

    @Override
    public void startJoinActivity() {
        startActivity(new Intent(this, JoinActivity.class));
    }
}

