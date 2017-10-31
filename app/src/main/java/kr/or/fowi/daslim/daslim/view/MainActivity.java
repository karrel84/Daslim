package kr.or.fowi.daslim.daslim.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import kr.or.fowi.daslim.daslim.R;
import kr.or.fowi.daslim.daslim.base.BaseActivity;
import kr.or.fowi.daslim.daslim.databinding.ActivityMainBinding;
import kr.or.fowi.daslim.daslim.view.adapter.MainPagerAdapter;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void onLoadOnce() {
        super.onLoadOnce();

        setupToolbar();
        setupBottomMenu();
        setupViewPager();
    }

    private void setupViewPager() {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        binding.container.setAdapter(adapter);
        binding.container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public int prePosition = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                prePosition = position;
                binding.bottomNavigation.getMenu().getItem(prePosition).setCheckable(false);
                binding.bottomNavigation.getMenu().getItem(position).setCheckable(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    // 하단메뉴 초기화
    private void setupBottomMenu() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_one:
                    binding.container.setCurrentItem(0);
                    return true;
                case R.id.action_two:
                    binding.container.setCurrentItem(1);
                    return true;
                case R.id.action_three:
                    binding.container.setCurrentItem(2);
                    return true;
            }
            return false;
        });
    }

    @Override
    protected void onLoad() {
        super.onLoad();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
    }


}