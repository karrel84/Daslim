package kr.or.fowi.daslim.daslim.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kr.or.fowi.daslim.daslim.R;
import kr.or.fowi.daslim.daslim.databinding.FragmentReserveBinding;
import kr.or.fowi.daslim.daslim.model.ScheduleInfo;
import kr.or.fowi.daslim.daslim.presenter.ReservePresenter;
import kr.or.fowi.daslim.daslim.presenter.ReservePresenterImpl;
import kr.or.fowi.daslim.daslim.view.adapter.SchedulePagerAdapter;

/**
 * Created by Rell on 2017. 10. 31..
 */

public class ReserveFragment extends Fragment implements ReservePresenter.View {

    private ReservePresenter presenter;
    private SchedulePagerAdapter adapter;
    private FragmentReserveBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new ReservePresenterImpl(this);
        setupViewPager();
        presenter.checkLogined();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_reserve, null, false);
        return binding.getRoot();
    }

    private void setupViewPager() {
        adapter = new SchedulePagerAdapter(getFragmentManager());
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
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

    @Override
    public void startLogin() {
        Intent intent = new Intent(getContext(), JoinActivity.class);
        startActivity(intent);
    }
}
