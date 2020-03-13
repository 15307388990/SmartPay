package com.ming.smartpay.fragment.mian;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.TypedValue;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.ming.smartpay.R;
import com.ming.smartpay.activity.SearchOrderActivity;
import com.ming.smartpay.base.fragment.BaseFragment;
import com.ming.smartpay.bean.StatisticsBean;
import com.ming.smartpay.config.MyConst;
import com.ming.smartpay.databinding.FragemtOrderBinding;
import com.ming.smartpay.fragment.OrderChildFragemt;
import com.ming.smartpay.utils.ParamTools;
import com.ming.smartpay.utils.Tools;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderFragemt extends BaseFragment {
    private FragemtOrderBinding binding;
    private List<Fragment> fragmentList;

    public static OrderFragemt newInstance() {
        return new OrderFragemt();
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View getLayoutRes() {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragemt_order, null, false);
        return binding.getRoot();
    }

    @Override
    public void initEvent() {
        fragmentList = new ArrayList<>();
        fragmentList.add(OrderChildFragemt.newInstance(1));
        fragmentList.add(OrderChildFragemt.newInstance(2));
        binding.viewpager.setAdapter(new Adaper(getChildFragmentManager()));
        final List<String> mTitleDataList = new ArrayList<>();
        mTitleDataList.add("未结订单");
        mTitleDataList.add("已结订单");
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(getResources().getColor(R.color.txt_title));
                colorTransitionPagerTitleView.setSelectedColor(getResources().getColor(R.color.SM_697BFE));
                colorTransitionPagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                // colorTransitionPagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                colorTransitionPagerTitleView.setText(mTitleDataList.get(i));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.viewpager.setCurrentItem(i);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(getActivity());
                indicator.setMode(2);
                indicator.setRoundRadius(5);
                indicator.setColors(getResources().getColor(R.color.SM_697BFE));//线的颜色
                indicator.setLineWidth(Tools.dip2px(getActivity(), 50));
                return indicator;
            }
        });
        binding.magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(binding.magicIndicator, binding.viewpager);
        binding.llSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.jump(getActivity(), SearchOrderActivity.class, false);

            }
        });

    }

    @Override
    public void initData() {
        getstatistics();

    }

    //统计订单数量-【初始化接口】
    public void getstatistics() {
        Map<String, String> map = new HashMap<>();
        mQueue.add(ParamTools.packParam(MyConst.getstatistics, getActivity(), this, this, map, Request.Method.GET));
        showLoadDialog();
    }

    @Override
    public void returnData(String data, String url) {
        if (url.contains(MyConst.getstatistics)) {
            StatisticsBean statisticsBean = JSON.parseObject(data, StatisticsBean.class);
            binding.tvNumberCount.setText("支付：" + statisticsBean.getData().getNumber_count());
            binding.tvSuccessCount.setText("成功：" + statisticsBean.getData().getSuccess_count());
            binding.tvSuccessRate.setText("成功率：" + statisticsBean.getData().getSuccess_rate() + "%");
            float fx = 0;
            try {
                fx = new BigDecimal((float) statisticsBean.getData().getSuccess_count() / statisticsBean.getData().getNumber_count()).
                        setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
            binding.circleZ.setPercentage(fx);
        }
    }

    class Adaper extends FragmentStatePagerAdapter {
        public Adaper(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }


}
