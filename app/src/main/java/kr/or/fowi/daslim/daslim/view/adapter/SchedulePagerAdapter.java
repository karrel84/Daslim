package kr.or.fowi.daslim.daslim.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.karrel.mylibrary.RLog;

import java.util.ArrayList;
import java.util.List;

import kr.or.fowi.daslim.daslim.model.ScheduleInfo;
import kr.or.fowi.daslim.daslim.view.ScheduleFragment;

/**
 * Created by Rell on 2017. 10. 11..
 */

public class SchedulePagerAdapter extends FragmentStatePagerAdapter {
    private final List<ScheduleFragment> fragments = new ArrayList<>();
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

    public void addFragment(ScheduleFragment tabFragment) {
        boolean isRemove = false;
        for (int i = 0; i < fragments.size(); i++) {
            if (fragments.get(i).getTitle().equals(tabFragment.getTitle())) {
                fragments.remove(i);
                fragments.add(i, tabFragment);
                isRemove = true;
                break;
            }
        }
        if (!isRemove) {
            fragments.add(tabFragment);
            titles.add(tabFragment.getTitle());
        }

        notifyDataSetChanged();
    }

    public void clearFragments() {
        fragments.clear();
        titles.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public boolean haveItem(String title) {
        for (String t2 : titles) {
            if (t2.equals(title)) return true;
        }

        return false;
    }

    public void updateFragment(ScheduleInfo info) {
        RLog.d("updateFragment");
        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).equals(info.title)) {
                fragments.get(i).updateInfo(info);
                break;
            }
        }
    }
}
