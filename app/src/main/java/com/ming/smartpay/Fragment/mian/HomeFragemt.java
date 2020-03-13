package com.ming.smartpay.fragment.mian;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.ming.smartpay.R;
import com.ming.smartpay.adapter.OrderAdapter;
import com.ming.smartpay.base.fragment.BaseFragment;
import com.ming.smartpay.bean.EventBusBean;
import com.ming.smartpay.bean.LineUpBean;
import com.ming.smartpay.bean.LongChainBean;
import com.ming.smartpay.bean.MianOrderBean;
import com.ming.smartpay.config.EventConstant;
import com.ming.smartpay.config.MyConst;
import com.ming.smartpay.databinding.FragemtHomeBinding;
import com.ming.smartpay.dialogfrment.CancelTheDealDialog;
import com.ming.smartpay.dialogfrment.CountdownDialog;
import com.ming.smartpay.utils.MyCountTimer;
import com.ming.smartpay.utils.ParamTools;
import com.ming.smartpay.utils.Tools;
import com.ming.smartpay.view.modelview.TimerOnback;


import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;


public class HomeFragemt extends BaseFragment {


    private FragemtHomeBinding binding;
    private OrderAdapter orderAdapter;
    private MyCountTimer myCountTimer;
    private boolean isbounced = false;

    public static HomeFragemt newInstance() {
        return new HomeFragemt();
    }


    //首页获取订单列表-【初始化接口】
    public void getneworder() {
        Map<String, String> map = new HashMap<>();
        mQueue.add(ParamTools.packParam(MyConst.getneworder, getActivity(), this, this, map, Request.Method.GET));
        showLoadDialog();
    }

    //首页获取排队位置
    public void getqrline() {
        Map<String, String> map = new HashMap<>();
        mQueue.add(ParamTools.packParam(MyConst.getqrline, getActivity(), this, this, map, Request.Method.GET));
        showLoadDialog();
    }

    //首页开始排队
    public void insertqrline() {
        Map<String, String> map = new HashMap<>();
        mQueue.add(ParamTools.packParam(MyConst.insertqrline, getActivity(), this, this, map, Request.Method.GET));
        showLoadDialog();
    }

    //取消排队
    public void outqrline() {
        Map<String, String> map = new HashMap<>();
        mQueue.add(ParamTools.packParam(MyConst.outqrline, getActivity(), this, this, map, Request.Method.GET));
        showLoadDialog();
    }

    //接受排队
    public void accept() {
        Map<String, String> map = new HashMap<>();
        mQueue.add(ParamTools.packParam(MyConst.accept, getActivity(), this, this, map, Request.Method.GET));
        showLoadDialog();
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
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragemt_home, null, false);
        return binding.getRoot();
    }

    @Override
    public void initEvent() {
        binding.llLineUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isbounced = false;
                insertqrline();
            }
        });
        binding.tvOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelTheDealDialog.newInstance().setTitle("是否退出排队状态").setContext("").setOktext("确认").setQtext("取消").setOnClickListener(new CancelTheDealDialog.OnClickListener() {
                    @Override
                    public void successful() {
                        outqrline();
                    }
                }).show(getActivity());

            }
        });

    }

    @Override
    public void returnData(String data, String url) {
        if (url.contains(MyConst.getneworder)) {
            MianOrderBean orderBean = JSON.parseObject(data, MianOrderBean.class);
            if (orderAdapter == null) {
                orderAdapter = new OrderAdapter(orderBean.getData().getList());
                binding.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                binding.recyclerview.setAdapter(orderAdapter);
            } else {
                orderAdapter.setNewData(orderBean.getData().getList());
            }
            binding.tvTodayAmount.setText(orderBean.getData().getToday_amount() + "");
            binding.tvTodayIncome.setText(orderBean.getData().getToday_income() + "");
            binding.tvTodayTotal.setText(orderBean.getData().getToday_total() + "");
        } else if (url.contains(MyConst.getqrline)) {
            LineUpBean lineUpBean = JSON.parseObject(data, LineUpBean.class);
            if (lineUpBean.getData() != null) {
                initLineUP(lineUpBean.getData());
            } else {
                outLine();
            }

        } else if (url.contains(MyConst.insertqrline)) {
            LineUpBean lineUpBean = JSON.parseObject(data, LineUpBean.class);
            initLineUP(lineUpBean.getData());

        } else if (url.contains(MyConst.outqrline)) {
            outLine();
        } else if (url.contains(MyConst.accept)) {

        }
    }

    /**
     * 初始化排队
     */
    private void initLineUP(final LongChainBean lineUpBean) {
        binding.llPay.setVisibility(View.VISIBLE);
        binding.tvLineUp.setText(lineUpBean.getQrname());
        if (lineUpBean.getChannel_name().contains("支付宝")) {
            binding.ivLineUp.setImageResource(R.mipmap.home_icon_a);
        } else {
            binding.ivLineUp.setImageResource(R.mipmap.home_icon_b);
        }
        binding.tvQrline.setText(lineUpBean.getQrline() + "");
        binding.rlLineup.setVisibility(View.VISIBLE);
        binding.llLineUp.setVisibility(View.GONE);
        if (myCountTimer != null) {
            myCountTimer.cancel();
            myCountTimer = null;
        }
        //开启定时器
        myCountTimer = new MyCountTimer(getActivity(), binding.tvTime, R.color.SM_6C42F5, Tools.getDateMs(lineUpBean.getEstimatedtime()), new TimerOnback() {
            @Override
            public void onFinish() {
            }

            @Override
            public void onTick(long millisUntilFinished) {
                //对比时间 是否弹框
                long promptingtime = Tools.getDateMs(lineUpBean.getPromptingtime());
                if (millisUntilFinished < promptingtime && lineUpBean.isIs_promptbox() && !isbounced) {
                    //去弹框
                    CountdownDialog.newInstance(lineUpBean.getCountdown() * 1000).setOnClickListener(new CountdownDialog.OnClickListener() {
                        @Override
                        public void successful() {
                            accept();
                        }

                        @Override
                        public void onFinish() {
                            outqrline();

                        }

                        @Override
                        public void cancel() {
                            outqrline();
                        }
                    }).show(getActivity());
                    isbounced = true;
                }

            }
        });
        myCountTimer.start();
    }

    /**
     * 取消排队 或者没有排队
     */
    private void outLine() {
        binding.llPay.setVisibility(View.GONE);
        binding.rlLineup.setVisibility(View.GONE);
        binding.llLineUp.setVisibility(View.VISIBLE);
        if (myCountTimer != null) {
            myCountTimer.cancel();
        }

    }

    @Subscribe
    public void onEventReceive(EventBusBean event) {
        if (EventConstant.OUTLINE.equals(event.getEventName())) {
            outLine();
        } else if (EventConstant.UPDATELINE.equals(event.getEventName())) {
            initLineUP(event.getData());
        } else if (EventConstant.ORDERS.equals(event.getEventName())) {
            //刷新
            getneworder();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myCountTimer != null) {
            myCountTimer.cancel();
        }
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Override
    public void initData() {
        getneworder();
        getqrline();

    }


}
