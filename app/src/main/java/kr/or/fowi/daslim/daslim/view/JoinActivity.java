package kr.or.fowi.daslim.daslim.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

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
}
