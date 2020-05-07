package com.ming.smartpay.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.ming.smartpay.R;
import com.ming.smartpay.activity.ChangeLoginPasswordActivity;
import com.ming.smartpay.activity.ChangePayPasswordActivity;
import com.ming.smartpay.activity.OrderDetailsActivity;
import com.ming.smartpay.adapter.OrderAdapter;
import com.ming.smartpay.base.fragment.BaseFragment;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.bean.MianOrderBean;
import com.ming.smartpay.bean.OrderBean;
import com.ming.smartpay.config.MyConst;
import com.ming.smartpay.databinding.FragemtMyBinding;
import com.ming.smartpay.databinding.FragemtOrderChildBinding;
import com.ming.smartpay.dialogfrment.OrderDialog;
import com.ming.smartpay.dialogfrment.VersionUpgradeDialog;
import com.ming.smartpay.utils.ParamTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ming.smartpay.config.MyConst.confirm;

public class OrderChildFragemt extends BaseFragment {
    private FragemtOrderChildBinding binding;
    private int pageNum = 1;
    private int pageSize = 10;
    private OrderAdapter orderAdapter;
    private List<OrderBean> beanList;
    private boolean isPage = true;

    public static OrderChildFragemt newInstance(int type) {
        OrderChildFragemt orderChildFragemt = new OrderChildFragemt();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        orderChildFragemt.setArguments(bundle);
        return orderChildFragemt;
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
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragemt_order_child, null, false);
        return binding.getRoot();
    }

    @Override
    public void initEvent() {
        binding.springView.setHeader(new DefaultHeader(getActivity()));
        binding.springView.setFooter(new DefaultFooter(getActivity()));
        beanList = new ArrayList<>();
        binding.springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
            }

            @Override
            public void onLoadmore() {
                pageNum++;
                if (isPage) {
                    initData();
                } else {
                    ToastShow.s("没有更多数据了");
                    binding.springView.onFinishFreshAndLoad();
                }

            }
        });
    }

    @Override
    public void initData() {
        if (getArguments().getInt("type") == 1) {
            getunfinishedlist();
        } else {
            getstatistics();
        }

    }

    //已结订单
    public void getstatistics() {
        Map<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        mQueue.add(ParamTools.packParam(MyConst.getorderslist, getActivity(), this, this, map, Request.Method.POST));
        showLoadDialog();
    }

    //未结订单
    public void getunfinishedlist() {
        Map<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum + "");
        map.put("pageSize", pageSize + "");
        mQueue.add(ParamTools.packParam(MyConst.getunfinishedlist, getActivity(), this, this, map, Request.Method.POST));
        showLoadDialog();
    }
    public void confirm(String amount, String order_no) {
        Map<String, String> map = new HashMap<>();
        map.put("amount", amount);
        map.put("order_no", order_no);
        mQueue.add(ParamTools.packParam(MyConst.confirm, getActivity(), this, this, map, Request.Method.POST));
        showLoadDialog();
    }

    @Override
    public void returnData(String data, String url) {
        if (url.contains(MyConst.getorderslist) || url.contains(MyConst.getunfinishedlist)) {
            binding.springView.onFinishFreshAndLoad();
            final MianOrderBean orderBean = JSON.parseObject(data, MianOrderBean.class);
            if (orderAdapter == null) {
                beanList = orderBean.getData().getList();
                orderAdapter = new OrderAdapter(beanList);
                binding.recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
                binding.recycleView.setAdapter(orderAdapter);
            } else {
                if (pageNum == 1) {
                    beanList = orderBean.getData().getList();
                } else {
                    beanList.addAll(orderBean.getData().getList());
                }
                orderAdapter.setNewData(orderBean.getData().getList());
            }
            if (beanList.size() < 10) {
                isPage = false;
            } else {
                isPage = true;
            }
            orderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    OrderBean obean = orderBean.getData().getList().get(position);
                    if (obean.getStatus() == 1) {
                        OrderDialog.newInstance(obean.getId() + "", obean.getOrder_no(), obean.getAmount(), obean.getPaymentname(), obean.getCreated_time())
                                .setOnClickListener(new OrderDialog.OnClickListener() {
                                    @Override
                                    public void toAccount(String amount, String order_no) {
                                        confirm(amount, order_no);
                                    }
                                }).show(getActivity());
                    } else if (orderBean.getData().getList().get(position).getStatus() == 3) {
                        Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
                        intent.putExtra("id", obean.getId() + "");
                        startActivity(intent);
                    }
                }
            });
        } else if (url.contains(MyConst.confirm)) {
            initData();
        }
    }
}
