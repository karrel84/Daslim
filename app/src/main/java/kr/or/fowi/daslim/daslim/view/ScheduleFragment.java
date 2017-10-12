package kr.or.fowi.daslim.daslim.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.or.fowi.daslim.daslim.R;
import kr.or.fowi.daslim.daslim.databinding.FragmentScheduleBinding;
import kr.or.fowi.daslim.daslim.model.ScheduleInfo;
import kr.or.fowi.daslim.daslim.presenter.SchedulePresenter;
import kr.or.fowi.daslim.daslim.presenter.SchedulePresenterImpl;
import kr.or.fowi.daslim.daslim.view.adapter.ScheduleListAdapter;


public class ScheduleFragment extends Fragment implements SchedulePresenter.View {
    private static final String SCHEDULE_INFO = "SCHEDULE_INFO";

    // presenter
    private SchedulePresenter presenter;
    // binding
    private FragmentScheduleBinding binding;
    // adapter
    private ScheduleListAdapter adapter;


    public ScheduleFragment() {
        presenter = new SchedulePresenterImpl(this);
    }

    public static ScheduleFragment newInstance(ScheduleInfo info) {
        // param must be not null!!
        if(info == null) throw new RuntimeException("parameter must be not null!!");
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putParcelable(SCHEDULE_INFO, info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ScheduleInfo info = getArguments().getParcelable(SCHEDULE_INFO);
            presenter.setData(info);
        }
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
}
