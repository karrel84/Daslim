package kr.or.fowi.daslim.daslim.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import kr.or.fowi.daslim.daslim.view.ScheduleFragment;

/**
 * Created by Rell on 2017. 10. 11..
 */

public class SchedulePagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> titles = new ArrayList<>();

    public SchedulePagerAdapter(FragmentManager fm) {
        super(fm);
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
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public void addFragment(ScheduleFragment tabFragment, String title) {
        fragments.add(tabFragment);
        titles.add(title);
        notifyDataSetChanged();
    }
}
