package kr.or.fowi.daslim.daslim.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.or.fowi.daslim.daslim.R;
import kr.or.fowi.daslim.daslim.databinding.FragmentScheduleBinding;
import kr.or.fowi.daslim.daslim.model.ReservationItem;
import kr.or.fowi.daslim.daslim.model.ScheduleInfo;
import kr.or.fowi.daslim.daslim.model.ScheduleInfoItem;
import kr.or.fowi.daslim.daslim.presenter.SchedulePresenter;
import kr.or.fowi.daslim.daslim.presenter.SchedulePresenterImpl;
import kr.or.fowi.daslim.daslim.view.adapter.ScheduleListAdapter;


public class ScheduleFragment extends Fragment implements SchedulePresenter.View {
    // presenter
    private SchedulePresenter presenter;
    // binding
    private FragmentScheduleBinding binding;
    // adapter
    private ScheduleListAdapter adapter;

    public ScheduleFragment(ScheduleInfo info) {
        presenter = new SchedulePresenterImpl(this);
        presenter.setData(info);
    }

    public static ScheduleFragment newInstance(ScheduleInfo info) {
        // param must be not null!!
        if (info == null) throw new RuntimeException("parameter must be not null!!");
        ScheduleFragment fragment = new ScheduleFragment(info);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false);
        // setup adapter
        setupAdapter();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.onViewCreated();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * setup adapter
     */
    private void setupAdapter() {
        adapter = new ScheduleListAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void addInfoItem(ScheduleInfoItem item) {
        // 아답터에 아이템을 추가하자
        adapter.addItem(item);
    }

    @Override
    public void addReserveItem(ReservationItem item) {
        adapter.addReserveItem(item);
    }

    public String getTitle() {
        return presenter.getTitle();
    }
}
