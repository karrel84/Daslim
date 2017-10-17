package kr.or.fowi.daslim.daslim.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import kr.or.fowi.daslim.daslim.R;
import kr.or.fowi.daslim.daslim.base.BaseActivity;
import kr.or.fowi.daslim.daslim.databinding.ActivityJoinBinding;
import kr.or.fowi.daslim.daslim.presenter.JoinPresenter;
import kr.or.fowi.daslim.daslim.presenter.JoinPresenterImpl;

public class JoinActivity extends BaseActivity implements JoinPresenter.View {

    private ActivityJoinBinding binding;
    private JoinPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join);
        presenter = new JoinPresenterImpl(this);
    }

    @Override
    protected void onLoadOnce() {
        super.onLoadOnce();
        setupButtons();
    }

    private void setupButtons() {
        binding.join.setOnClickListener(view -> {
            String name = binding.name.getText().toString();
            String nick = binding.nick.getText().toString();
            // join
            presenter.join(name, nick);
        });
    }

    @Override
    public void onBackPressed() {
        // TODO: 2017. 10. 17. 팝업을 띄워야한다
    }

    @Override
    public Context getContext() {
        return this;
    }
}
