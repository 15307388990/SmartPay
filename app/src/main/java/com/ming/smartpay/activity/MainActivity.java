package com.ming.smartpay.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.ming.smartpay.base.activity.BaseActivity;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.bean.EventBusBean;
import com.ming.smartpay.bean.LongChainBean;
import com.ming.smartpay.config.EventConstant;
import com.ming.smartpay.config.MyConst;
import com.ming.smartpay.databinding.ActivityMainBinding;
import com.ming.smartpay.fragment.mian.HomeFragemt;
import com.ming.smartpay.fragment.mian.MyFragemt;
import com.ming.smartpay.fragment.mian.OrderFragemt;
import com.ming.smartpay.fragment.mian.CoreFragemt;
import com.ming.smartpay.fragment.mian.RankingFragemt;
import com.ming.smartpay.R;
import com.ming.smartpay.utils.ParamTools;
import com.ming.smartpay.ws.WsManager;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    private Fragment[] fragmentList;
    private HomeFragemt homeFragemt;
    private CoreFragemt coreFragemt;
    private RankingFragemt rankingFragemt;
    private OrderFragemt orderFragemt;
    private MyFragemt myFragemt;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String text = (String) msg.obj;
                if (!TextUtils.isEmpty(text)) {
                    LongChainBean bean = JSON.parseObject(text, LongChainBean.class);
                    if (bean.getType().equals("init")) {
                        binduid(bean.getClient_id());
                    } else if (bean.getType().equals("outline")) {
                        EventBus.getDefault().post(new EventBusBean(EventConstant.OUTLINE));
                    } else if (bean.getType().equals("updateline")) {
                        EventBus.getDefault().post(new EventBusBean(EventConstant.UPDATELINE, bean));
                    } else if (bean.getType().equals("orders")) {
                        EventBus.getDefault().post(new EventBusBean(EventConstant.ORDERS, bean));
                    }
                }
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WsManager.getInstance().init();
        initView();

    }

    @Override
    public View getLayoutRes() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        return binding.getRoot();
    }

    @Override
    public void initEvent() {

    }

    //接受排队
    public void binduid(String client_id) {
        Map<String, String> map = new HashMap<>();
        map.put("client_id", client_id);
        mQueue.add(ParamTools.packParam(MyConst.binduid, MainActivity.this, this, this, map, Request.Method.POST));
        showLoadDialog();
    }

    @Override
    public void onTextMessage(final String text) {
        super.onTextMessage(text);
        new Thread() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.obj = text;
                message.what = 1;
                handler.sendMessage(message);
            }
        }.start();


    }

    @Override
    public void initData() {
    }

    @Override
    protected void returnData(String data, String url) {
        super.returnData(data, url);
        if (url.contains(MyConst.binduid)) {
            ToastShow.s("绑定成功");
        }
    }

    private void initView() {
        homeFragemt = HomeFragemt.newInstance();
        coreFragemt = CoreFragemt.newInstance();
        orderFragemt = OrderFragemt.newInstance();
        rankingFragemt = RankingFragemt.newInstance();
        myFragemt = MyFragemt.newInstance();
        fragmentList = new Fragment[]{homeFragemt, coreFragemt, rankingFragemt, orderFragemt, myFragemt};
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        showFragment(homeFragemt);
                        break;
                    case R.id.rb_2:
                        showFragment(coreFragemt);
                        break;
//                    case R.id.rb_3:
//                        showFragment(rankingFragemt);
//                        break;
                    case R.id.rb_4:
                        showFragment(orderFragemt);
                        break;
                    case R.id.rb_5:
                        showFragment(myFragemt);
                        break;
                }

            }
        });
        showFragment(homeFragemt);

    }

    /**
     * 显示fragment
     */
    private void showFragment(Fragment fragment) {
        if (!fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.framelayout, fragment, fragment.getClass().getName()).commitAllowingStateLoss();
            for (Fragment f : fragmentList) {
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                if (f == fragment) {
                    if (fragment.isHidden()) {
                        transaction2.show(f).commitAllowingStateLoss();
                    }
                } else {
                    if (f.isAdded()) {
                        transaction2.hide(f).commitAllowingStateLoss();
                    }
                }
            }
        } else {
            for (Fragment f : fragmentList) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (f == fragment) {
                    if (fragment.isHidden()) {
                        transaction.show(f).commitAllowingStateLoss();
                    }
                } else {
                    if (f.isAdded()) {
                        transaction.hide(f).commitAllowingStateLoss();
                    }
                }
            }

        }
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
