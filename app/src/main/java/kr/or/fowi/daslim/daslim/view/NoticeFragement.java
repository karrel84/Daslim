package kr.or.fowi.daslim.daslim.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.or.fowi.daslim.daslim.R;
import kr.or.fowi.daslim.daslim.databinding.FragmentNoticeBinding;

/**
 * Created by Rell on 2017. 10. 31..
 */

public class NoticeFragement extends Fragment {

    private FragmentNoticeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 2017. 10. 31. binding
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notice, null, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO: 2017. 10. 31. setupData
    }
}
