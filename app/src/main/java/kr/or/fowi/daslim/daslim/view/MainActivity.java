package kr.or.fowi.daslim.daslim.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import kr.or.fowi.daslim.daslim.R;
import kr.or.fowi.daslim.daslim.base.BaseActivity;
import kr.or.fowi.daslim.daslim.databinding.ActivityMainBinding;
import kr.or.fowi.daslim.daslim.model.ScheduleInfo;
import kr.or.fowi.daslim.daslim.presenter.MainPresenter;
import kr.or.fowi.daslim.daslim.presenter.MainPresenterImpl;
import kr.or.fowi.daslim.daslim.view.adapter.SchedulePagerAdapter;

public class MainActivity extends BaseActivity implements MainPresenter.View {

    private ActivityMainBinding binding;
    private MainPresenter presenter;
    private SchedulePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new MainPresenterImpl(this);
    }

    @Override
    protected void onLoadOnce() {
        super.onLoadOnce();

        setupToolbar();
        setupViewPager();
    }

    private void setupViewPager() {
        adapter = new SchedulePagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        presenter.checkLogined();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
    }

    @Override
    public void startLogin() {
        Intent intent = new Intent(this, JoinActivity.class);
        startActivity(intent);
    }

    @Override
    public void clearFragment() {
        adapter.clearFragments();
    }

    @Override
    public void setFragment(List<ScheduleInfo> scheduleInfos) {
        for (ScheduleInfo info : scheduleInfos) {
            adapter.addFragment(ScheduleFragment.newInstance(info));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        if (binding.progressBar.getVisibility() != View.GONE) {
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}