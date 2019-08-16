package com.ming.smartpay.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ming.smartpay.Fragment.mian.HomeFragemt;
import com.ming.smartpay.Fragment.mian.MyFragemt;
import com.ming.smartpay.Fragment.mian.ThereFragemt;
import com.ming.smartpay.Fragment.mian.TwoFragemt;
import com.ming.smartpay.R;
import com.ming.smartpay.base.activity.MvpActivity;
import com.ming.smartpay.base.permission.PermissionHelper;
import com.ming.smartpay.presenter.MainPresenter;
import com.ming.smartpay.view.MianView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpActivity<MianView, MainPresenter> {


    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.rb_1)
    RadioButton rb1;
    @BindView(R.id.rb_2)
    RadioButton rb2;
    @BindView(R.id.rb_3)
    RadioButton rb3;
    @BindView(R.id.rb_4)
    RadioButton rb4;
    List<Fragment> fragmentList;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragemt.newInstance());
        fragmentList.add(TwoFragemt.newInstance());
        fragmentList.add(ThereFragemt.newInstance());
        fragmentList.add(MyFragemt.newInstance());
        viewPager.setAdapter(new MyfAdatper(getSupportFragmentManager(), fragmentList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        radioGroup.check(R.id.rb_1);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_2);
                        break;
                    case 2:
                        radioGroup.check(R.id.rb_3);
                        break;
                    case 3:
                        radioGroup.check(R.id.rb_4);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_4:
                        viewPager.setCurrentItem(3);
                        break;

                }

            }
        });
        PermissionHelper.reqCameraAndSDcard(MainActivity.this,null);
    }


    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    class MyfAdatper extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public MyfAdatper(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragmentList = fragments;
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
