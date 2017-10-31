package kr.or.fowi.daslim.daslim.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import kr.or.fowi.daslim.daslim.view.InfoFragment;
import kr.or.fowi.daslim.daslim.view.NoticeFragement;
import kr.or.fowi.daslim.daslim.view.ReserveFragment;

/**
 * Created by Rell on 2017. 10. 31..
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragments = new ArrayList<>();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments.add(new InfoFragment());
        fragments.add(new ReserveFragment());
        fragments.add(new NoticeFragement());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
